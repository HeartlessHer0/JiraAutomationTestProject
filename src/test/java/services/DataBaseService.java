package services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DataBaseService {
    Logger logger = LoggerFactory.getLogger(DataBaseService.class);

    static final String HOST = "abul.db.elephantsql.com";
    static final String PORT = "5432";
    static final String USER = System.getProperty("DB_USER");
    static final String PSW = System.getProperty("DB_PASSWORD");
    static final String DATABASE_NAME = "vxzwtbyw";

    static final String JDBC = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE_NAME;

    Connection connection = null;
    Statement statement = null;

    public DataBaseService() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.info(e.toString());
            return;
        }

        try {
            connection = DriverManager.getConnection(JDBC, USER, PSW);
        } catch (SQLException throwables) {
            logger.info(throwables.toString());
        }

        if (connection != null) {
            logger.info("You successfully connected to database.");
        } else {
            logger.info("Something went wrong!");
        }
    }
    
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            logger.info(throwables.toString());
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public Statement getStatement() {
        try {
            if (statement == null) {
                statement = getConnection().createStatement();
            }
        } catch (SQLException ex) {
            logger.info("Can`t create a statement!");
        }

        return statement;
    }

    public void executeSql(String sql) {
        try {
            logger.info("Query execution result: " + getStatement().execute(sql));
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public ResultSet executeQuery(String sql) {
        try {
            return getStatement().executeQuery(sql);
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }

        return null;
    }
}
