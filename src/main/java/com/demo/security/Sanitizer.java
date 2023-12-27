package com.demo.security;

public interface Sanitizer<T> {
  T sanitize(T t);
}
