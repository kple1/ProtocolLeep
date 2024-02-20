package io.leeple.network.SQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

    public boolean equalDataBaseName(Connection connection, String dbName) throws SQLException {
        Statement statement = connection.createStatement();
        String checkDatabaseQuery = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '" + dbName + "'";
        return statement.executeQuery(checkDatabaseQuery).next();
    }

    public void dropDB(Connection connection, String dbName) throws SQLException {
        Statement statement = connection.createStatement();
        String createDatabaseQuery = "DROP DATABASE " + dbName;
        statement.executeUpdate(createDatabaseQuery);
        statement.close();
    }

    public void createDB(Connection connection, String dbName) throws SQLException {
        Statement statement = connection.createStatement();
        String createDatabaseQuery = "CREATE DATABASE " + dbName;
        statement.executeUpdate(createDatabaseQuery);
        statement.close();
    }

    public void txtDataInput(Connection connection, String path, String column, String[] tables, String dbName) throws SQLException {
        Statement statement = connection.createStatement();

        String useDatabaseQuery = "USE " + dbName;
        statement.executeUpdate(useDatabaseQuery);

        for (int i = 0; i < 8; i++) {
            statement.executeUpdate("LOAD DATA LOCAL INFILE '" + path + tables[i] + ".txt' INTO table " + tables[i]);
        }
        statement.close();
    }

    public void imageDataInput(Connection connection, String dbName, int loop, String imgTableName, String imgTableColumn, String path) throws SQLException {
        Statement statement = connection.createStatement();

        String useDatabaseQuery = "USE " + dbName;
        statement.executeUpdate(useDatabaseQuery);

        for (int i = 0; i < loop; i++) {
            statement.executeUpdate("INSERT INTO" + imgTableName + "(" + imgTableColumn + ") values (LOAD_FILE('" + path + i + ".jpg'))");
        }
        statement.close();
    }
}

