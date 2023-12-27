package com.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DemoService {

  public String buildMessage(String name) {
    return "Hello " + name + ". This is a sample spring boot template project.";
  }
}
