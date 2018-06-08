package com.example.demo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/api/hello")
public class HelloResource {
    private static Logger logger = LoggerFactory.getLogger(HelloResource.class);

    @GET
    public String hello() {
        logger.debug("sayHello");
        return "hello";
    }
}
