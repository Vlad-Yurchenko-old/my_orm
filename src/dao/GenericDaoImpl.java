package dao;

import dao.invoke.InvokerFactory;
import domain.BaseEntity;
import schema.loader.xml.*;
import schema.loader.xml.models.xml.Database;

import java.io.File;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

public class GenericDaoImpl<T extends BaseEntity> implements Dao<T> {

    static {
        Database database = null;
        try {
            database = new XMLSchemaLoader(new File("library_bd.xml")).parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Connector.init(database.getDriver(), database.getUrl(), database.getUserName(), database.getUserPass());
    }

    private Database database;
    private Table table;
    private Class<T> templateClass;
    private ArrayList<Method> methods;

    private GenericDaoImpl() {
    }

    public GenericDaoImpl(Class<T> templateClass) {
        this.templateClass = templateClass;

        database = Database.getInstance();
        for (Table tableIter : database.getTables()) {
            if (tableIter.getEntityName().compareTo(templateClass.getSimpleName()) == 0) {
                table = tableIter;
            }
        }

        methods = new ArrayList<Method>(Arrays.asList(templateClass.getDeclaredMethods()));
        methods.addAll(Arrays.asList(templateClass.getSuperclass().getDeclaredMethods()));
    }

    @Override
    public List<T> readAll() {
        String sql = "SELECT * FROM " + table.getName();
        Connection c = null;
        Statement s = null;
        ResultSet r = null;
        try {
            c = Connector.getConnection();
            s = c.createStatement();
            r = s.executeQuery(sql);

            List<T> list = new ArrayList<>();
            while (r.next()) {
                T entity = templateClass.getConstructor().newInstance();
                for (Field field : table.getFields()) {
                    String setMethodName = getSetMethodByName(field.getEntityField());
                    for (Method method : methods) {
                        if (method.getName().compareTo(setMethodName) == 0) {
                            InvokerFactory.getInvoker(method.getParameterTypes()[0]).invoke(method, entity, r, field.getName());
                            break;
                        }
                    }
                }
                /*
                * Устанавливаем внешние ключи
                * */
                for (ForeignKey fk : table.getFKs()) {
                    String setMethodName = getSetMethodByName(fk.getField().getEntityField());
                    for (Method method : methods) {
                        if (method.getName().compareTo(setMethodName) == 0) {
                            InvokerFactory.getInvoker(method.getParameterTypes()[0]).invoke(method, entity, r, fk.getField().getName());
                            break;
                        }
                    }
                }
                /*
                * Устанавливаем id
                * */
                for (Method method : methods) {
                    if (method.getName().compareTo(getSetMethodByName(table.getPK().getEntityField())) == 0) {
                        InvokerFactory.getInvoker(method.getParameterTypes()[0]).invoke(method, entity, r, table.getPK().getName());
                        break;
                    }
                }

                list.add(entity);
            }
            return list;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                r.close();
                s.close();
                c.close();
            } catch (NullPointerException | SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public int create(T entity) {
        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO " + table.getName() + "(");
        for (Field field : table.getFields()) {
            sqlBuilder.append(field.getName() + ",");
        }
        sqlBuilder.replace(sqlBuilder.length() - 1, sqlBuilder.length(), ") VALUES (");
        for (int i = 0; i < table.getFields().size(); i++) {
            sqlBuilder.append("?,");
        }
        sqlBuilder.replace(sqlBuilder.length() - 1, sqlBuilder.length(), ")");

        Connection connection;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Connector.getConnection();
            statement = connection.prepareStatement(sqlBuilder.toString(), PreparedStatement.RETURN_GENERATED_KEYS);

            int i = 1;
            for (Field field : table.getFields()) {
                for (Method method : methods) {
                    if (method.getName().compareTo(getGetMethodByName(field.getEntityField())) == 0) {
                        InvokerFactory.getInvoker(method.getParameterTypes()[0]).setStatement(method, statement, i, entity);
                        i++;
                    }
                }
            }
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (NullPointerException | SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        //TODO: возвращать статус?
        return 0;
    }

    @Override
    public T read(Object id) {
        String sql = "SELECT * FROM " + table.getName() + " WHERE " + table.getPK().getName() + " = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Connector.getConnection();
            statement = connection.prepareStatement(sql);

            for (Method method : methods) {
                /*
                * Ищем метод возвращающий идентификатор,
                * далее берем тип возвращаемого значения и на основе него
                * устанавливаем соответствующий тип
                * */
                if (method.getName().compareTo(getGetMethodByName(table.getPK().getName())) == 0) {
                    switch (method.getReturnType().getName()) {
                        case "int":
                            statement.setInt(1, (Integer) id);
                            break;
                        case "java.lang.String":
                            statement.setString(1, (String) id);
                            break;
                        case "java.lang.Object":
                            statement.setObject(1, id);
                            break;
                    }
                    break;
                }
            }
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                T entity = templateClass.getConstructor().newInstance();
                for (Field field : table.getFields()) {
                    String setMethodName = getSetMethodByName(field.getEntityField());
                    for (Method method : methods) {
                        if (method.getName().compareTo(setMethodName) == 0) {
                            InvokerFactory.getInvoker(method.getParameterTypes()[0]).invoke(method, entity, resultSet, field.getName());
                            break;
                        }
                    }
                }
                /*
                * Устанавливаем id
                * */
                for (Method method : methods) {
                    if (method.getName().compareTo(getSetMethodByName(table.getPK().getEntityField())) == 0) {
                        InvokerFactory.getInvoker(method.getParameterTypes()[0]).invoke(method, entity, resultSet, table.getPK().getName());
                        break;
                    }
                }
                return entity;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (NullPointerException | SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public int update(T entity) {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE " + table.getName() + " SET");
        for (Field field : table.getFields()) {
            sqlBuilder.append(" " + field.getName() + " = ?, ");
        }
        sqlBuilder.replace(sqlBuilder.length() - 2, sqlBuilder.length(), " WHERE " + table.getPK().getName() + " = ?");

        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = Connector.getConnection();
            statement = connection.prepareStatement(sqlBuilder.toString());

            int i = 1;
            for (Field field : table.getFields()) {
                for (Method method : methods) {
                    if (method.getName().compareTo(getGetMethodByName(field.getEntityField())) == 0) {
                        InvokerFactory.getInvoker(method.getParameterTypes()[0]).setStatement(method, statement, i, entity);
                        i++;
                    }
                }
            }
            for (Method method : methods) {
                if (method.getName().compareTo(getGetMethodByName(table.getPK().getEntityField())) == 0) {
                    InvokerFactory.getInvoker(method.getParameterTypes()[0]).setStatement(method, statement, i, entity);
                    break;
                }
            }
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) {
                e.printStackTrace();
            }
        }
        //TODO: возвращать статус
        return 0;
    }

    @Override
    public void delete(Object id) {
        String sql = "DELETE FROM " + table.getName() + " WHERE " + table.getPK().getName() + " = ?";
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = Connector.getConnection();
            statement = connection.prepareStatement(sql);
            for (Method method : methods) {
                if (method.getName().compareTo(getGetMethodByName(table.getPK().getEntityField())) == 0) {
                    switch (method.getReturnType().getName()) {
                        case "int":
                            statement.setInt(1, (Integer) id);
                            break;
                        case "java.lang.String":
                            statement.setString(1, (String) id);
                            break;
                        case "java.lang.Object":
                            statement.setObject(1, id);
                            break;
                    }
                    break;
                }
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (NullPointerException | SQLException e) {
            }
        }
    }

    private String getSetMethodByName(String name) {
        String methodName = "set";
        methodName += String.valueOf(name.charAt(0)).toUpperCase();
        methodName += name.substring(1);
        return methodName;
    }

    private String getGetMethodByName(String name) {
        String methodName = "get";
        methodName += String.valueOf(name.charAt(0)).toUpperCase();
        methodName += name.substring(1);
        return methodName;
    }

}
