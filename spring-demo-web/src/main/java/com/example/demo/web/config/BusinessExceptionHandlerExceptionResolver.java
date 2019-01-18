package com.example.demo.web.config;

import com.example.demo.core.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BusinessExceptionHandlerExceptionResolver extends AbstractHandlerExceptionResolver {
  private static  final Logger logger =
      LoggerFactory.getLogger(BusinessExceptionHandlerExceptionResolver.class);

  @Override
  protected ModelAndView doResolveException(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    try {
      if (!(ex instanceof BusinessException)) {
        return null;
      }
      if (!StringUtils.hasLength(ex.getMessage())) {
        response.sendError(HttpStatus.BAD_REQUEST.value());
      } else {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
      }
      return new ModelAndView();
    } catch (Exception e) {
      logger.warn("Failure while trying to resolve exception", e);
    }
    return null;
  }

  public BusinessExceptionHandlerExceptionResolver() {
    setOrder(Integer.MIN_VALUE);
  }
}
