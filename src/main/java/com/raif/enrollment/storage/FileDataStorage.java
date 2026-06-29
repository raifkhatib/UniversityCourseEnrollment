package com.raif.enrollment.storage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Handles saving and loading application data from a local file.
 * This separates file storage logic from the user interface and services.
 */
public class FileDataStorage {
    private static final Path DATA_FILE = Path.of("data", "enrollment-data.ser");

    public void save(DataSnapshot snapshot) throws IOException {
        Files.createDirectories(DATA_FILE.getParent());

        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(DATA_FILE))) {
            outputStream.writeObject(snapshot);
        }
    }

    public Optional<DataSnapshot> load() throws IOException, ClassNotFoundException {
        if (!Files.exists(DATA_FILE)) {
            return Optional.empty();
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(DATA_FILE))) {
            Object data = inputStream.readObject();

            if (data instanceof DataSnapshot snapshot) {
                return Optional.of(snapshot);
            }

            return Optional.empty();
        }
    }

    public Path getDataFilePath() {
        return DATA_FILE;
    }
}