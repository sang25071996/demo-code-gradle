package com.demo.common.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CommonResponseTest {

  @Test
  void testSuccessHaveMessage() {
    CommonResponse<String> actual = CommonResponse.success("test", "successfully");
    Assertions.assertEquals("success", actual.getStatus());
    Assertions.assertEquals("test", actual.getData());
    Assertions.assertEquals("successfully", actual.getMessage());
  }

  @Test
  void testSuccessNoMessage() {
    CommonResponse<List<String>> actual = CommonResponse.success(new ArrayList<>());
    Assertions.assertEquals("success", actual.getStatus());
    Assertions.assertEquals(0, actual.getData().size());
    Assertions.assertNull(actual.getMessage());
  }

  @Test
  void testSuccessMessageOnly() {
    CommonResponse<String> actual = CommonResponse.success("test");
    Assertions.assertEquals("success", actual.getStatus());
    Assertions.assertEquals("test", actual.getMessage());
    Assertions.assertNull(actual.getData());
  }

  @Test
  void testSuccessOnly() {
    CommonResponse<String> actual = CommonResponse.success();
    Assertions.assertEquals("success", actual.getStatus());
    Assertions.assertNull(actual.getMessage());
    Assertions.assertNull(actual.getData());
  }
}
