package com.example.demo.core.repository;

import com.example.demo.core.entity.Echo;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;

public interface EchoRepository extends JpaRepositoryImplementation<Echo, Long> {
    Optional<Echo> findByEcho(String echo);
}
