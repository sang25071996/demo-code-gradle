package com.demo.security;

import org.owasp.encoder.Encode;
import org.springframework.web.util.HtmlUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class SanitizerUtils {

  private SanitizerUtils() {
  }

  public static String sanitizeString(String untrustedString) {
    if (untrustedString == null) {
      return null;
    }
    return Encode.forHtmlContent(HtmlUtils.htmlEscape(untrustedString));
  }

  public static List<String> sanitizeStringList(List<String> untrustedStringList) {
    if (untrustedStringList != null) {
      return untrustedStringList.stream().map((SanitizerUtils::sanitizeString)).toList();
    }
    return untrustedStringList;
  }

  public static String[] sanitizeStringArray(String[] untrustedStringArray) {
    if (untrustedStringArray != null && untrustedStringArray.length > 0) {
      return Arrays.stream(untrustedStringArray).map((SanitizerUtils::sanitizeString)).toArray(String[]::new);
    }
    return untrustedStringArray;
  }

  public static Path sanitizePathTraversal(String filePath) {
    Path p = Paths.get(filePath);
    return p.toAbsolutePath();
  }

  public static byte[] sanitizeFileContent(byte[] bytes) {
    String base64 = Base64.getEncoder().encodeToString(bytes);
    return Base64.getDecoder().decode(base64);
  }
}
