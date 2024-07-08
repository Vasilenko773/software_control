package ru.software.control;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ClassesLoader {

    private final List<Class<?>> classes;

    public static ClassesLoader read(String packageName) {
        ClassLoader classLoader = Thread.currentThread()
                .getContextClassLoader();

        return new ClassesLoader(loadedClassesFrom(packageName, classLoader));

    }

    private static List<Class<?>> loadedClassesFrom(String packageName, ClassLoader loader) {
        String validPath = packageName.replace('.', '/');
        return loader.resources(validPath)
                .map(url -> new File(url.getFile()))
                .flatMap(dir -> clasessFrom(dir, packageName).stream())
                .toList();
    }

    @SneakyThrows
    private static List<Class<?>> clasessFrom(File directory, String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        packageName = packageName.concat(".");
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isDirectory()) {
                List<Class<?>> classesFromDir = clasessFrom(file, packageName.concat(file.getName()));
                classes.addAll(classesFromDir);
            } else if (file.getName().endsWith(".class")) {
                String className = classNameWithoutExtension(file);
                classes.add(Class.forName(packageName.concat(className)));
            }
        }
        return classes;
    }

    private static String classNameWithoutExtension(File file) {
        return file.getName().substring(0, file.getName().length() - 6);
    }

    public List<Class<?>> classes() {
        return classes;
    }
}
