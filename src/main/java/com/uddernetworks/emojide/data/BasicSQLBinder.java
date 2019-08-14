package com.uddernetworks.emojide.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class BasicSQLBinder implements SQLBinder {

    private static Logger LOGGER = LoggerFactory.getLogger(BasicSQLBinder.class);

    @Override
    public void createBindings(Object object) {
        Arrays.stream(object.getClass().getDeclaredFields())
                .parallel()
                .filter(field -> field.isAnnotationPresent(SQLBound.class))
                .filter(field -> field.getType().equals(String.class))
                .forEach(field -> {
                    var sqlBoundFile = field.getAnnotation(SQLBound.class).file();
                    var sqlFile = (sqlBoundFile.isBlank() ? field.getName() : sqlBoundFile) + ".sql";
                    field.setAccessible(true);
                    try {
                        field.set(object, getQuery(sqlFile));
                    } catch (IllegalAccessException | IOException e) {
                        LOGGER.error("Error setting field " + field.getName() + " in " + object.getClass().getSimpleName(), e);
                    }
                });
    }

    @Override
    public String getQuery(String name) throws IOException {
        LOGGER.info("Resource {}", name);
        var resource = Objects.requireNonNull(getClass().getClassLoader().getResource(name));

        try (var reader = new BufferedReader(new InputStreamReader(resource.openStream()))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }
}
