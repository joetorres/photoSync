package software.joe.photoSync.model;

public record Media(String hash, String originalFilePath, ProviderType provider) {
}
