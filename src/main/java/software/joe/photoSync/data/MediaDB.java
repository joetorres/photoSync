package software.joe.photoSync.data;

import software.joe.photoSync.model.Media;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MediaDB {
    public boolean fileAlreadyExists(String hash) {
        boolean exists = false;
        try {
            Connection con = DB.getDataBase();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM media WHERE fileHash like ?");
            statement.setString(1, hash);
            ResultSet rs = statement.executeQuery();
            exists = rs.next();
            statement.close();
            con.close();
        } catch (SQLException ignored) { }

        return exists;
    }

    public void LogFile(Media media) {
        try
        {
            Connection con = DB.getDataBase();
            PreparedStatement statement = con.prepareStatement("INSERT INTO media (fileHash, originalFilePath, provider) VALUES (?, ?, ?)");
            statement.setString(1, media.fileHash());
            statement.setString(2, media.originalFilePath());
            statement.setInt(3, media.provider().getValue());
            statement.execute();
            statement.close();
            con.close();
        } catch (SQLException ignored) { }
    }
}
