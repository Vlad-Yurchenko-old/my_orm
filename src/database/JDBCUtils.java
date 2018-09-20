package database;

import dao.Connector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class JDBCUtils {

    public static Optional<Connection> getConnetcion() {
        try {
            return Optional.of(Connector.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static Optional<Statement> createStatement() {
        try {
            return createStatement(Connector.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static Optional<Statement> createStatement(Connection connection) {
        try {
            return Optional.of(connection.createStatement());
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
