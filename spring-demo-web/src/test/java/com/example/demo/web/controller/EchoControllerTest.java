package com.example.demo.web.controller;

import com.example.demo.core.service.EchoService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EchoController.class)
@WithMockUser
public class EchoControllerTest {
  @Autowired private MockMvc mockMvc;

  @MockBean private EchoService echoService;

  @Test
  @Tag("fast")
  public void testPubGet() throws Exception {
    doNothing().when(echoService).echo();
    this.mockMvc
        .perform(get("/api/pub/echo").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
