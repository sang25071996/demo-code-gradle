package com.demo.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DemoService.class)
class DemoServiceTest {

  @Autowired
  DemoService service;

  @Test
  void testBuildMessage() {
    String expected = "Hello test-user. This is a sample spring boot template project.";
    Assert.assertEquals(expected, service.buildMessage("test-user"));
  }
}
