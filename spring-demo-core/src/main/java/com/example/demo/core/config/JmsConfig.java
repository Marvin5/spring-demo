package com.example.demo.core.config;

import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.connection.TransactionAwareConnectionFactoryProxy;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

@Configuration
@EnableJms
@ConfigurationProperties(prefix = "demo.jms")
public class JmsConfig {
  private String username;
  private String password;
  private String url;

  @Bean
  public ConnectionFactory connectionFactory() throws Exception {
    ActiveMQConnectionFactory acf = ActiveMQJMSClient.createConnectionFactory(url,"marvin");
    acf.setUser(username);
    acf.setPassword(password);
    CachingConnectionFactory ccf = new CachingConnectionFactory(acf);
    return new TransactionAwareConnectionFactoryProxy(ccf);
  }

  @Bean("jmsTransactionManager")
  public PlatformTransactionManager jmsTransactionManager() throws Exception {
    JmsTransactionManager txManager = new JmsTransactionManager();
    txManager.setConnectionFactory(connectionFactory());
    return txManager;
  }

  @Bean
  public JmsTemplate jmsTemplate() throws Exception {
    return new JmsTemplate(connectionFactory());
  }

  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() throws Exception {
    DefaultJmsListenerContainerFactory containerFactory = new DefaultJmsListenerContainerFactory();
    containerFactory.setConnectionFactory(connectionFactory());
    containerFactory.setTransactionManager(jmsTransactionManager());
    return containerFactory;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
