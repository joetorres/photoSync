package software.joe.photoSync.provider;

import software.joe.photoSync.model.ProviderType;

import java.util.ArrayList;

public abstract class BaseProvider {
    private final ProviderType type;

    public ProviderType getProviderType() {
        return type;
    }

    public BaseProvider(ProviderType type) {
        this.type = type;
    }

    public abstract ArrayList<String> ListFiles();
    public abstract boolean DeleteFile(String filePath);
    public abstract boolean fileExists(String filePath);
    public abstract String DownloadFile(String filePath, String destinationFolder);
}
