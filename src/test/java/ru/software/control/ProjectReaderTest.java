package ru.software.control;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectReaderTest {

    @Test
    void projectScannerLoadClasses() throws Exception {
        ProjectReader reader = new ProjectReader();

        List<Class<?>> classes = reader.classes("ru.software.control");

        assertThat(classes)
                .isNotEmpty()
                .hasSize(4);

    }
}
