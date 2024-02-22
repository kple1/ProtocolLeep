package io.leeple.network.SQL;

import java.sql.Connection;
import java.sql.ResultSet;
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

    public void txtDataInput(Connection connection, String path, String[] tables, String dbName) throws SQLException {
        Statement statement = connection.createStatement();

        String useDatabaseQuery = "USE " + dbName;
        statement.executeUpdate(useDatabaseQuery);

        for (int i = 0; i < tables.length; i++) {
            statement.executeUpdate("LOAD DATA LOCAL INFILE '" + path + tables[i] + ".txt' INTO table " + tables[i]);
        }
        statement.close();
    }

    public void imageDataInput(Connection connection, String dbName, int loop, String imgTableName, String imgTableColumn, String path) throws SQLException {
        Statement statement = connection.createStatement();

        String useDatabaseQuery = "USE " + dbName;
        statement.executeUpdate(useDatabaseQuery);

        for (int i = 0; i < loop; i++) {
            statement.executeUpdate("INSERT INTO " + imgTableName + "(" + imgTableColumn + ") values (LOAD_FILE('" + path + i + ".jpg'))");
        }
        statement.close();
    }

    public void createUser(Connection connection, String user, String password) throws SQLException{
        Statement st = connection.createStatement();
        String createUser = "CREATE USER '" + user +"'@'localhost' IDENTIFIED BY '" + password +"'";
        st.executeUpdate(createUser);
        st.close();
    }

    public static String getString(String column, String tableName, Connection connection, String dbName, String getNumColumn, int id) {
        String name = "";

        try {
            Statement statement = connection.createStatement();
            String useDatabaseQuery = "USE " + dbName;
            statement.executeUpdate(useDatabaseQuery);
            String query = "SELECT " + column + " FROM " + tableName + " WHERE " + getNumColumn + " = " + id;
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                name = resultSet.getString(column);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public static int getInt(String column, String tableName, Connection connection, String dbName, String getNumColumn, int id) {
        int value = 0;
        try {
            Statement statement = connection.createStatement();
            String useDatabaseQuery = "USE " + dbName;
            statement.executeUpdate(useDatabaseQuery);
            String query = "SELECT " + column + " FROM " + tableName + " WHERE " + getNumColumn + " = " + id;
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                value = resultSet.getInt(column);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }
}

