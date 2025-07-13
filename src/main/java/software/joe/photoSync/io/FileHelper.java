package software.joe.photoSync.io;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public class FileHelper {
    public static String getMD5Checksum(String filename) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        InputStream is = new FileInputStream(filename);
        byte[] buffer = new byte[1024];
        int read;
        while ((read = is.read(buffer)) != -1) {
            md.update(buffer, 0, read);
        }
        is.close();

        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
