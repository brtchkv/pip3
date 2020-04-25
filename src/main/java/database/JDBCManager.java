package main.java.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JDBCManager {
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private Connection connection = null;

    public JDBCManager(String dataBaseURL, String user, String password, boolean autoCommit) {
        try {
            setConnection(DriverManager.getConnection(dataBaseURL, user, password));
            getConnection().setAutoCommit(autoCommit);

            PreparedStatement ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS " +
                    "results (" +
                    "id bigserial primary key," +
                    "x double precision NOT NULL," +
                    "y double precision NOT NULL," +
                    "r double precision NOT NULL," +
                    "match boolean NOT NULL)");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    private void setConnection(Connection connection) {
        this.connection = connection;
    }
}
