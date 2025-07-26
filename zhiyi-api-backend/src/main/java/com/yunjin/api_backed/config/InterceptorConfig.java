package com.yunjin.api_backed.config;

/**
 * Spring MVC的资源处理器（ResourceHttpRequestHandler）来映射一个特定的URL路径到一个文件系统目录。
 *
 * @anther 韵锦
 * @from
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        final String imgPath = "/files/**";
        File localDir = new File("files");
        registry.addResourceHandler(imgPath)
                .addResourceLocations("file:" + localDir.getAbsolutePath() + File.separator);
    }
}
