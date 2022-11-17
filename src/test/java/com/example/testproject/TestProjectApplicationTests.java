package com.example.testproject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestProjectApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yaml")
class TestProjectApplicationTests {

  @Autowired
  private MockMvc mvc;

  @Test
  void getAllPeople() throws Exception {
    mvc.perform(get("/api/person-management/people")
          .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$[0].firstName").value("Bogdan"))
      .andExpect(jsonPath("$[0].lastName").value("Zaranik"))
      .andExpect(jsonPath("$[1].firstName").value("Maxim"))
      .andExpect(jsonPath("$[1].lastName").value("Borisov"));
  }

  @Test
  void getPersonById() throws Exception {
    mvc.perform(get("/api/person-management/people/1")
          .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.firstName").value("Bogdan"))
      .andExpect(jsonPath("$.lastName").value("Zaranik"))
      .andExpect(jsonPath("$.ageInYears").value(20L));
  }

  @Test
  void noSuchPersonFound() throws Exception {
    mvc.perform(get("/api/person-management/people/-1000")
          .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isNotFound())
      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.description").value("Person was not found"));
  }


}
