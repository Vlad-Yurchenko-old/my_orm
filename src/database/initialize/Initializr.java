package database.initialize;

import dao.Connector;
import database.exception.InitializrException;
import model.DBField;
import model.DBTable;
import model.DB_TYPE;
import model.Database;
import query.mysql.CreateTableQueryBuilder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static database.JDBCUtils.createStatement;

//TODO: прикрутить LOGGER
//TODO: переделать на парсер
public class Initializr {

    static {
        System.out.println("STATIC INIT");

        Database database = new Database();
        database.setDriver("com.mysql.jdbc.Driver");
        database.setName("myorm");
        database.setUserName("root");
        database.setUserPass("root");
        database.setUrl("jdbc:mysql://localhost/myorm");
        database.setTables((ArrayList<DBTable>) Stream.of(
                new DBTable("authorA", (ArrayList<DBField>) Stream.of(
                        new DBField("name", DB_TYPE.VARCHAR),
                        new DBField("surname", DB_TYPE.VARCHAR)
                ).collect(Collectors.toList()))
        ).collect(Collectors.toList()));


        Connection connection;
        try {
//            database = new XMLSchemaLoader(new File("schema.xml")).parse();
            Connector.init(database.getDriver(), database.getUrl(), database.getUserName(), database.getUserPass());
            connection = Connector.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw new InitializrException();
        }


        CreateTableQueryBuilder createTableQueryBuilder = new CreateTableQueryBuilder();
        database.getTables().forEach(table ->
                createStatement(connection).ifPresent(
                        statement -> {
                            try {
                                statement.execute(createTableQueryBuilder.build(table));
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                )
        );

    }

}
