package com.demo.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class SwaggerConfigTest {
  @InjectMocks
  private SwaggerConfig config;

  @Test
  void customOpenAPI() {
    assertNotNull(config.customOpenAPI(),
          "custom config should be callable");
  }
}
