package com.example.demo.core.repository;

import com.example.demo.core.entity.Echo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
// @ContextConfiguration()
public class EchoRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EchoRepository repository;

    @Test
    public void testExample() throws Exception {
        Echo entity = new Echo();
        entity.setEcho("echo");
        this.entityManager.persist(entity);
        Echo echo = repository.findByEcho("echo").orElse(null);
        System.out.println(echo);
    }
}