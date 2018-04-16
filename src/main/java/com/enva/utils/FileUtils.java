package com.enva.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public final class FileUtils {

   public static String getFileContextFromResource(Class<?> clazz, String file) throws URISyntaxException, IOException {
        URI filePath = Objects.requireNonNull(clazz.getClassLoader().getResource(file)).toURI();
        return new String(Files.readAllBytes(Paths.get(filePath)), "UTF-8");
    }

    private FileUtils() {
    }
}
