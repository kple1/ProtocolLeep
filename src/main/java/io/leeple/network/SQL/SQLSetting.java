package io.leeple.network.SQL;

import java.sql.SQLException;

public interface SQLSetting {
    void dataInput() throws SQLException;
    void databaseExists() throws SQLException;
    void dropDataBase() throws SQLException;
    void createDataBase() throws SQLException;
    void auth() throws SQLException;
    void createTable() throws SQLException;
}
