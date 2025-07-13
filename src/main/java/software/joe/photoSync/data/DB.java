package software.joe.photoSync.data;

import software.joe.photoSync.util.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    public static Connection getDataBase() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + Constants.appDataDirectory + "/media.db");
    }
}
