package com.demo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This is the annotation that will be used by interceptor to default to the default API version which is the
 * last stable version. This will only be used in the scenario where the request does not provide a version in
 * the accept header.
 *
 * @author Username
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Default {

  /**
   * This property provides a regex to identify the request url pattern.
   */
  String value() default "";

  /**
   * This property provides a default version for the APIs.
   */
  String defaultApiVersion() default "";
}
