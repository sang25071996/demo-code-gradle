package com.demo.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.owasp.encoder.Encode;
import org.springframework.web.util.HtmlUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class SanitizerUtilsTest {
  private String untrustedString;
  private List<String> untrustedStringList = new ArrayList<>();
  private String expectedHTMLString;
  private String[] untrustedStringArray;

  @BeforeEach
  public void setUp() {
    untrustedString = "<script>alert('XSS Attack');</script>";
    untrustedStringList.add(untrustedString);
    expectedHTMLString = Encode.forHtmlContent(HtmlUtils.htmlEscape(untrustedString));
    untrustedStringArray = new String[] { untrustedString };
  }

  @Test
  void testSanitizeString() {
    String actual = SanitizerUtils.sanitizeString(untrustedString);
    assertEquals(expectedHTMLString, actual);
  }

  @Test
  void testSanitizeStringList() {
    List<String> actual = SanitizerUtils.sanitizeStringList(untrustedStringList);
    List<String> expected = new ArrayList<>();
    expected.add(expectedHTMLString);
    assertEquals(expected, actual);
  }

  @Test
  void testSanitizeStringArray() {
    String[] actual = SanitizerUtils.sanitizeStringArray(untrustedStringArray);
    String[] expected = new String[] { expectedHTMLString };
    assertEquals(expected[0], actual[0]);
  }

  @Test
  void testSanitizePathTraversal() {
    String filePath = "application-test.yaml";
    Path result = SanitizerUtils.sanitizePathTraversal(filePath);

    assertEquals(Paths.get(filePath).toAbsolutePath(), result);
  }

  @Test
  void testSanitizeStringReturnNull() {
    String actual = SanitizerUtils.sanitizeString(null);
    assertNull(actual);
  }

  @Test
  void testSanitizeStringArrayIsEmpty() {
    String[] emptyStringArray = new String[0];
    String[] actual = SanitizerUtils.sanitizeStringArray(emptyStringArray);
    assertArrayEquals(new String[0], actual);
  }

  @Test
  void testSanitizeStringListIsNull() {
    List<String> actual = SanitizerUtils.sanitizeStringList(null);
    assertNull(actual);
  }

  @Test
  void testSanitizeStringArrayIsNull() {
    String[] actual = SanitizerUtils.sanitizeStringArray(null);
    assertNull(actual);
  }

  @Test
  void testSanitizeFileContent() {
    byte[] inputBytes = "Hello, World!".getBytes();
    String base64String = "SGVsbG8sIFdvcmxkIQ==";
    byte[] expectedBytes = Base64.getDecoder().decode(base64String);

    byte[] result = SanitizerUtils.sanitizeFileContent(inputBytes);

    assertArrayEquals(expectedBytes, result);
  }
}
