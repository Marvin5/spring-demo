package com.example.demo.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class OAuth2ServerConfig {

  @Configuration
  @EnableAuthorizationServer
  public static class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired private SecurityConfig securityConfig;

    @Bean
    public TokenStore tokenStore() {
      return new InMemoryTokenStore();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
      security.allowFormAuthenticationForClients();
      security.addTokenEndpointAuthenticationFilter(
          new CorsFilter(securityConfig.configurationSource()));
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
      clients
          .inMemory()
          .withClient("webApp")
          .secret("webAppSecret")
          .authorizedGrantTypes(
              OAuthConstant.IMPLICIT, OAuthConstant.PASSWORD, OAuthConstant.REFRESH_TOKEN)
          .authorities("ROLE_CLIENT")
          .scopes("READ", "WRITE")
          .redirectUris("http://example.com")
          .autoApprove("READ");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
      endpoints
          .tokenStore(tokenStore())
          .authenticationManager(securityConfig.authenticationManagerBean());
    }
  }

  @Configuration
  @EnableResourceServer
  public static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
      // empty
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
      http.requestMatchers()
          .antMatchers("/api/**")
          .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          .and()
          .authorizeRequests()
          .antMatchers("/api/pub/**")
          .permitAll()
          .antMatchers("/api/**")
          .access("#oauth2.hasScope('read') or (!#oauth2.isOAuth() and hasRole('ROLE_USER'))");
    }
  }
}
