package com.example.demo;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdkTest {
    private static final Logger logger = LoggerFactory.getLogger(JdkTest.class);

    @Test
    public void test() {
        logger.info("result is {} ", Long.valueOf("11101", 2));
    }
}
