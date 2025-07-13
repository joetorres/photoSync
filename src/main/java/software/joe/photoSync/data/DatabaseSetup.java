package software.joe.photoSync.data;

import software.joe.photoSync.util.Constants;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseSetup {
    public static void Initialize() {

        try {
            Path appDataDir = Paths.get(Constants.appDataDirectory);
            Files.createDirectories(appDataDir);

            Connection con = DB.getDataBase();

            PreparedStatement statement = con.prepareStatement("CREATE TABLE IF NOT EXISTS media ( " +
                    "fileHash TEXT PRIMARY KEY, originalFilePath TEXT, provider INTEGER );");

            statement.execute();
            con.close();
        } catch (Exception ignored) {

        }
    }
}
