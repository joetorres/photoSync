package software.joe.photoSync.model;

import software.joe.photoSync.provider.BaseProvider;

public enum ProviderType {
    FileSystem(1),
    ICloud(2),
    GooglePhotos(3),
    OneDrive(4);

    private final int value;

    ProviderType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ProviderType fromValue(int value) {
        for (ProviderType s : ProviderType.values()) {
            if (s.getValue() == value) {
                return s;
            }
        }
        throw new IllegalArgumentException("Invlaid value: " + value);
    }
}

