package com.study.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 资源映射路径
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Value("${examination.image.path:}")
    private String uploadFilePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations("file:"+uploadFilePath);
    }
}
