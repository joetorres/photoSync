package software.joe.photoSync.io;

import software.joe.photoSync.model.Media;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
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
    public static String getFileName(String fullFileName) {
        return Path.of(fullFileName).getFileName().toString();
    }
    public static String getFileNameWithoutExtension(String fullFileName) {
        return getFileName(fullFileName).replaceFirst("[.][^.]+$", "");
    }
    public static String getFileExtension(String fullFileName) {
        String fileName = getFileName(fullFileName);
        int pos = fileName.lastIndexOf('.');
        if (pos > 0 && pos < fileName.length() - 1) {
            return fileName.substring(pos + 1);
        }
        return "";
    }

    public static void MoveFile(String fileFullPath, String destinationFolder, Media media) {
        Path destiny = Path.of(destinationFolder);
        String extension = media.fileExtension() == null || media.fileExtension().isEmpty() ? "" : ("." + media.fileExtension());
        destiny = destiny.resolve(media.fileHash() + extension);
        try {
            Files.move(Path.of(fileFullPath), destiny);
        } catch (IOException ignored) { }
    }
}
