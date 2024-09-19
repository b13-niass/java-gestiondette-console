package com.example.odc.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class AppConfig {
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer properties() {
//        PropertySourcesPlaceholderConfigurer propertyConfigurer = new PropertySourcesPlaceholderConfigurer();
//        propertyConfigurer.setLocation(new ClassPathResource("application.properties"));
//        return propertyConfigurer;
//    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();

        YamlPropertiesFactoryBean yamlFactoryBean = new YamlPropertiesFactoryBean();
        Resource yamlResource = new ClassPathResource("application.yml");
        yamlFactoryBean.setResources(yamlResource);

        propertySourcesPlaceholderConfigurer.setProperties(yamlFactoryBean.getObject());
        return propertySourcesPlaceholderConfigurer;
    }
}
