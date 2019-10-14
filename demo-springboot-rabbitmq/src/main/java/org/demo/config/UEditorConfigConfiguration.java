package org.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties
@Configuration
@ConfigurationProperties(prefix = "file.upload.config")
@Data
public class UEditorConfigConfiguration {
    private String imageUrl;
    private String imagePath;
    private String imageFieldName;
    private String imageMaxSize;
    private String[] imageAllowFiles;
}
