package software.joe.photoSync.provider;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class FileSystemProvider implements IProvider {
    private final String directoryPath;

    public FileSystemProvider(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    @Override
    public ArrayList<String> ListFiles() {
        Collection<File> files = FileUtils.listFiles(new File(directoryPath), null, true);
        return files
                .stream()
                .filter(f -> !f.isDirectory())
                .map(File::getAbsolutePath)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public boolean DeleteFile(String filePath) {
        try {
            Files.delete(Path.of(filePath));
            return true;
        } catch (IOException ignored) {
            return false;
        }
    }

    @Override
    public boolean fileExists(String filePath) {
        return Files.exists(Path.of(filePath));
    }

    @Override
    public boolean DownloadFile(String filePath, String destinationFolder) {
        try {
            Files.copy(Path.of(filePath), Path.of(destinationFolder));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
