package com.demo.controller;

import com.demo.common.dto.CommonResponse;
import com.demo.request.ItemRequest;
import com.demo.response.GreetingResponse;
import com.demo.security.SanitizerUtils;
import com.demo.service.DemoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1")
@Slf4j
@Tag(name = "DhpDevxTestsangAuth")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "OK"),
    @ApiResponse(responseCode = "201", description = "Created"),
    @ApiResponse(responseCode = "401", description = "Unauthorized"),
    @ApiResponse(responseCode = "403", description = "Forbidden"),
    @ApiResponse(responseCode = "404", description = "Not Found") })
public class DemoController {

  @Autowired
  DemoService service;

  @Operation(summary = "Get welcome")
  @GetMapping(value = "/welcome")
  public ResponseEntity<CommonResponse<GreetingResponse>> getGreeting(@RequestParam("name") String name) {
    log.info("Welcome API called with " + name);
    String message = service.buildMessage(SanitizerUtils.sanitizeString(name));
    GreetingResponse greeting = GreetingResponse.builder().greeting(message).build();
    return ResponseEntity.ok(CommonResponse.success(greeting, "Greeting successfully"));
  }

  @Operation(summary = "Get all items")
  @GetMapping(value = "/item")
  public ResponseEntity<CommonResponse<List<String>>> getItems() {
    log.info("GetItems API called with items: " + new ArrayList<>());
    return ResponseEntity.ok(CommonResponse.success(new ArrayList<>(), "Get all items successfully"));
  }

  @Operation(summary = "Get an item by id")
  @GetMapping(value = "/item/{id}")
  public ResponseEntity<CommonResponse<List<String>>> getItemById(@PathVariable Long id) {
    log.info("GetItems API called with id: " + id);
    return ResponseEntity.ok(CommonResponse.success(new ArrayList<>(), "Get item successfully"));
  }

  @Operation(summary = "Save an item")
  @PostMapping(value = "/item")
  public ResponseEntity<CommonResponse<List<String>>> saveItem(@RequestBody ItemRequest request) {
    log.info("SaveItem API called with item: " + request);
    ItemRequest sanitizerRequest = request.sanitize(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.success(new ArrayList<>(), "Create item successfully"));
  }

  @Operation(summary = "Delete an item")
  @DeleteMapping(value = "/item/{id}")
  public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
    log.info("DeleteItem API called with id: " + id);
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Update an item")
  @PutMapping(value = "/item/{id}")
  public ResponseEntity<CommonResponse<List<String>>> updateItem(@PathVariable Long id, @RequestBody ItemRequest request) {
    log.info("UpdateItem API called with id: " + id + "; item: " + request);
    ItemRequest sanitizerRequest = request.sanitize(request);
    return ResponseEntity.ok(CommonResponse.success(new ArrayList<>(), "Update item successfully"));
  }
}
