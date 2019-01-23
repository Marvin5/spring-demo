package com.example.demo.core.repository;

import com.example.demo.core.entity.Echo;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface EchoRepository extends JpaRepositoryImplementation<Echo, Long> {}
