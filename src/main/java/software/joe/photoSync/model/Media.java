package software.joe.photoSync.model;

public record Media(String fileHash, String originalFilePath, ProviderType provider, String fileExtension) {
}
