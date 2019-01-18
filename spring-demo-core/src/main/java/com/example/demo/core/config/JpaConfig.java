package com.example.demo.core.config;

import com.google.common.collect.ImmutableMap;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
    enableDefaultTransactions = false,
    basePackages = {"com.example.demo.core.repository", "com.example.demo.core.admin.repository"})
@EnableJpaAuditing
@ConfigurationProperties("demo.db")
public class JpaConfig {
  private String url;
  private String username;
  private String password;

  @Bean
  public DataSource dataSource() {
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setJdbcUrl(url);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    return new TransactionAwareDataSourceProxy(dataSource);
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(true);
    vendorAdapter.setShowSql(true);
    vendorAdapter.setDatabase(Database.MYSQL);
    // using mysql 8 and default engine is innodb
    vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("com.example.demo.core.entity", "com.example.demo.core.admin.entity");
    factory.setDataSource(dataSource());
    factory.setJpaPropertyMap(
        ImmutableMap.<String, String>builder()
            .put(
                AvailableSettings.PHYSICAL_NAMING_STRATEGY,
                SpringPhysicalNamingStrategy.class.getName())
            .build());
    return factory;
  }

  @Bean("jpaTransactionManager")
  public PlatformTransactionManager jpaTransactionManager(EntityManagerFactory emf) {
    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(emf);
    return txManager;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
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
}
