package com.demo.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {
  private String status;
  private T data;
  private String message;

  private CommonResponse() {
    
  }

  private CommonResponse(String status, T data, String message) {
    this.status = status;
    this.data = data;
    this.message = message;
  }

  public static <T> CommonResponse<T> success(T data, String message) {
    return new CommonResponse<>("success", data, message);
  }

  public static <T> CommonResponse<T> success(T data) {
    return success(data, null);
  }

  public static <T> CommonResponse<T> success(String message) {
    return success(null, message);
  }

  public static <T> CommonResponse<T> success() {
    return success(null, null);
  }

  public static <T> CommonResponse<T> error(String message) {
    return new CommonResponse<>("error", null, message);
  }
}
