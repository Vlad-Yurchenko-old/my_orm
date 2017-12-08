package dao.invoke;

import domain.BaseEntity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Invoker {
    <T extends BaseEntity> void invoke(Method method, T entity, ResultSet resultSet, String resultSetValue) throws SQLException, InvocationTargetException, IllegalAccessException;

    <T extends BaseEntity> void setStatement(Method method, PreparedStatement statement, int i, T entity) throws InvocationTargetException, IllegalAccessException, SQLException;
}
