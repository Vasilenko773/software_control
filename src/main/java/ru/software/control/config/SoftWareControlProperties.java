package ru.software.control.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@ConfigurationProperties("software.control")
public class SoftWareControlProperties {

    /**
     * To specify the vulnerability search package
     */

    private String packageScan;
}
