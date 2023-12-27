package com.demo.controller;

import com.demo.service.DemoService;
import com.demo.request.ItemRequest;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = DemoController.class)
class DemoControllerTest {

  @MockBean
  DemoService service;
  @Autowired
  private MockMvc mockMvc;

  @Test
  void testGetGreeting() throws Exception {

    Mockito.when(service.buildMessage("test-user"))
        .thenReturn("Hello test-user. This is a sample spring boot template project.");

    mockMvc.perform(MockMvcRequestBuilders
        .get("/v1/welcome?name=test-user"))
        .andExpect(status().isOk());
    Mockito.verify(service, times(1)).buildMessage("test-user");

  }

  @Test
  void testGetItems() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
        .get("/v1/item"))
        .andExpect(status().isOk());
  }

  @Test
  void testGetItemById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
        .get("/v1/item/1"))
        .andExpect(status().isOk());
  }

  @Test
  void testSaveItem() throws Exception {
    ItemRequest request = new ItemRequest();

    mockMvc.perform(MockMvcRequestBuilders
        .post("/v1/item")
        .content(new Gson().toJson(request))
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isCreated());
  }

  @Test
  void testDeleteItem() throws Exception {
    ItemRequest request = new ItemRequest();

    mockMvc.perform(MockMvcRequestBuilders
        .delete("/v1/item/1"))
        .andExpect(status().isNoContent());
  }

  @Test
  void testUpdateItem() throws Exception {
    ItemRequest request = new ItemRequest();

    mockMvc.perform(MockMvcRequestBuilders
        .put("/v1/item/1")
        .content(new Gson().toJson(request))
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk());
  }
}
