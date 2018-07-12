package com.example.demo.config;

import org.glassfish.jersey.server.ResourceConfig;

//@Configuration
public class JerseyConfig {
    // @Bean
    public ResourceConfig resourceConfig() {
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.packages("com.example.demo.rest");
        return resourceConfig;

    }
}
