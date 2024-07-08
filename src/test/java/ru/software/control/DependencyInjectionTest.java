package ru.software.control;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import ru.software.control.config.ApplicationConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@ContextConfiguration(classes = ApplicationConfiguration.class)
class DependencyInjectionTest {

    @Autowired
    ClassesLoader classesLoader;

    @Test
    void contextInitBeanAndReadScan() {
        assertThat(classesLoader.classes())
                .isNotEmpty()
                .hasSize(7);
    }
}
