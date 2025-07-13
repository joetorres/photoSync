package software.joe.photoSync.provider;

import java.util.ArrayList;
import java.util.Collection;

public interface IProvider {
    public ArrayList<String> ListFiles();
    public boolean DeleteFile(String filePath);
    public boolean fileExists(String filePath);
    public boolean DownloadFile(String filePath, String destinationFolder);
}
