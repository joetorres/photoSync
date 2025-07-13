package software.joe.photoSync.worker;

import software.joe.photoSync.data.MediaDB;
import software.joe.photoSync.io.FileHelper;
import software.joe.photoSync.model.Media;
import software.joe.photoSync.model.ProviderType;
import software.joe.photoSync.provider.BaseProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class EliminateDupplicationWorker implements IWorker {
    private final String destinationFolderPath;
    private final ArrayList<BaseProvider> providers;
    private final String tempFolderPath;
    private final MediaDB db;

    public EliminateDupplicationWorker(String destinationFolderPath, ArrayList<BaseProvider> providers) {
        this.destinationFolderPath = destinationFolderPath;
        this.providers = providers;
        Path base = Path.of(this.destinationFolderPath);
        Path tempPath = base.resolve("temp");

        try {
            Files.createDirectories(tempPath);
        } catch (IOException ignored) { }

        this.tempFolderPath =  tempPath.toFile().getAbsolutePath();
        this.db = new MediaDB();
    }

    @Override
    public void ExecuteWork() {
        for(BaseProvider p : providers) {
            ArrayList<String> allFiles = p.ListFiles();

            for(String f : allFiles) {
               String tmpFile = p.DownloadFile(f, this.tempFolderPath);
                try {
                    Media media = new Media(
                            FileHelper.getMD5Checksum(tmpFile),
                            f,
                            p.getProviderType(),
                            FileHelper.getFileExtension(f));

                    //see if file already exists
                    if(!db.fileAlreadyExists(media.fileHash())) {
                        //log the file
                        db.LogFile(media);

                        //move the file
                        FileHelper.MoveFile(tmpFile, destinationFolderPath, media);
                    }
                } catch (Exception ignored) { }
            }
        }
    }
}
