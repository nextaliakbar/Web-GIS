package com.web.apps;

import java.io.File;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer{
    
    private final static String ROOT_DIR;
    
    static {
        ROOT_DIR = Paths.get("").toAbsolutePath().resolve("upload").toString();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadPath = "file:" + ROOT_DIR + File.separator;        
        registry.addResourceHandler("/upload/**")
               .addResourceLocations(uploadPath)
               .setCachePeriod(0); // Disable caching untuk development
    }
}
