package com.demo;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("integration-test")
public class DemoApplicationIntegrationTest {

  @Test
  public void contextLoads() {
    Assertions.assertTrue(true);
  }

}
