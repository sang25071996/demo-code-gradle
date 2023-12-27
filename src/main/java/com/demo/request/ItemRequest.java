package com.demo.request;

import com.demo.security.Sanitizer;
import com.demo.security.SanitizerUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ItemRequest implements Sanitizer<ItemRequest> {

  private String id;
  private String name;


  @Override
  public ItemRequest sanitize(ItemRequest itemRequest) {
    ItemRequest sanitizer = new ItemRequest();
    sanitizer.id = SanitizerUtils.sanitizeString(itemRequest.id);
    sanitizer.name = SanitizerUtils.sanitizeString(itemRequest.name);
    return sanitizer;
  }
}
