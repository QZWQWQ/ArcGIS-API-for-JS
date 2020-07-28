package com.njwpdi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * springboot 静态资源目录配置
 * 
 * @author sunjing
 * @date
 */
@Configuration
public class ResourcePathConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private GolbalVariableConfig golbalVariableConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // addResourceHandler中的是访问路径，可以修改为其他的字符串
        // addResourceLocations中的是实际路径
        registry.addResourceHandler("/externalImage/**")
            .addResourceLocations("file:" + golbalVariableConfig.getImagePath());
        registry.addResourceHandler("/temporaryPath/**")
            .addResourceLocations("file:" + golbalVariableConfig.getTemporaryPath());
        super.addResourceHandlers(registry);
    }
}
