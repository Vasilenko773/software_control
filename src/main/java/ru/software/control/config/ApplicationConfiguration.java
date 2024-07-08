package ru.software.control.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.software.control.ClassesLoader;

@Configuration
@EnableConfigurationProperties(SoftWareControlProperties.class)
@ConditionalOnClass(SoftWareControlProperties.class)
public class ApplicationConfiguration {

    @Bean
    @ConditionalOnProperty(name = "software.control.package-scan")
    public ClassesLoader classesLoader(@Value("${software.control.package-scan}") String packageScan) {
        return ClassesLoader.read(packageScan);
    }
}
