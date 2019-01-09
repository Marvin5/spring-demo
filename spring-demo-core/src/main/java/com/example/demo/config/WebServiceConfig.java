package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;

@Configuration
public class WebServiceConfig {
    @Bean
    public SimpleJaxWsServiceExporter simleJaxWsServiceExporter() {
        SimpleJaxWsServiceExporter exporter = new SimpleJaxWsServiceExporter();
        exporter.setBaseAddress("http://localhost:5002/ws/");
        return exporter;
    }
}
