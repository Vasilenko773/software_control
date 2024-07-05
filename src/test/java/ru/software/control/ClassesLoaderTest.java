package ru.software.control;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ClassesLoaderTest {

    @Test
    void projectScannerLoadClasses() throws Exception {
        ClassesLoader reader = new ClassesLoader("ru.software.control");

        List<Class<?>> classes = reader.classes();

        assertThat(classes)
                .isNotEmpty()
                .hasSize(5);

    }
}
