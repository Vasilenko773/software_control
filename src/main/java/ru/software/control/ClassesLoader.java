package ru.software.control;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ClassesLoader {

    private final String packageName;

    public List<Class<?>> classes() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        return classLoader != null
                ? loadedClassesFrom(classLoader)
                : List.of();
    }

    private List<Class<?>> loadedClassesFrom(ClassLoader classLoader) {
        String validPath = packageName.replace('.', '/');
        return classLoader.resources(validPath)
                .map(url -> new File(url.getFile()))
                .flatMap(dir -> clasessFrom(dir, packageName).stream())
                .toList();
    }

    @SneakyThrows
    private List<Class<?>> clasessFrom(File directory, String packageName) {
        List<Class<?>> classes = new ArrayList<>();

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isDirectory()) {
                classes.addAll(clasessFrom(file, packageName.concat(".").concat(file.getName())));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
