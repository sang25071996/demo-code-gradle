package com.demo.service;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoService.class)
public class DemoServiceIntegrationTest {

  @Autowired
  private DemoService service;

  @Test
  public void testBuildMessage() {
    String expected = "Hello test-user. This is a sample spring boot template project.";
    Assert.assertEquals(expected, service.buildMessage("test-user"));
  }
}
