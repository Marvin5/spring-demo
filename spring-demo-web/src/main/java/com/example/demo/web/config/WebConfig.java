package com.example.demo.web.config;

import com.example.demo.core.exception.BusinessException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
  @Bean
  public HandlerExceptionResolver businessExceptionHandlerExceptionResolver() {
    return new BusinessExceptionHandlerExceptionResolver();
  }

  /**
   * 配置MVC前缀
   */
  @Override
  @SuppressWarnings({"unchecked"})
  public void configurePathMatch(PathMatchConfigurer configurer) {
    configurer
        .setUseTrailingSlashMatch(false)
        .addPathPrefix(
            "/api",
            new HandlerTypePredicate.Builder()
                .basePackage("com.example.demo.web.controller")
                .annotation(RestController.class)
                .build());
  }
}
