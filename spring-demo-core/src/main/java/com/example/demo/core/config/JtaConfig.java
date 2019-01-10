package com.example.demo.core.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

@Configuration
@ConditionalOnProperty(havingValue = "true", prefix = "demo.jta", value = "enable")
public class JtaConfig {
  @Bean("jtaTransactionManager")
  public PlatformTransactionManager jtaTransactionManager() {
    JtaTransactionManager jtaTxManager = new JtaTransactionManager();
    UserTransaction userTransaction = new UserTransactionImp();
    TransactionManager userTxManager = new UserTransactionManager();
    jtaTxManager.setUserTransaction(userTransaction);
    jtaTxManager.setTransactionManager(userTxManager);
    return jtaTxManager;
  }
}
