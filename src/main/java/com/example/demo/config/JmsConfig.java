package com.example.demo.config;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.connection.TransactionAwareConnectionFactoryProxy;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ConfigurationProperties(prefix = "demo.jms")
public class JmsConfig {
  private String username;
  private String password;
  private String url;

  @Bean
  public ConnectionFactory connectionFactory() throws JMSException {
    ActiveMQConnectionFactory acf = new ActiveMQConnectionFactory();
    acf.setBrokerURL(url);
    acf.setUserName(username);
    acf.setPassword(password);
    CachingConnectionFactory ccf = new CachingConnectionFactory(acf);
    return new TransactionAwareConnectionFactoryProxy(ccf);
  }

  @Bean("jmsTransactionManager")
  public PlatformTransactionManager jmsTransactionManager() throws JMSException {
    JmsTransactionManager txManager = new JmsTransactionManager();
    txManager.setConnectionFactory(connectionFactory());
    return txManager;
  }

  @Bean
  public JmsTemplate jmsTemplate() throws JMSException {
    return new JmsTemplate(connectionFactory());
  }

  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() throws JMSException {
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
