package com.example.testproject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.testproject.dtos.request.PersonDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestProjectApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yaml")
public class PeopleCreateDeleteTests {

  @Autowired
  MockMvc mvc;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  @Order(1)
  public void createPerson() throws Exception {
    String firstName = "Eugene";
    String lastName = "Suleimanov";
    LocalDate dateOfBirth = LocalDate.of(1998, Month.NOVEMBER, 29);

    PersonDto dto = new PersonDto(firstName, lastName, dateOfBirth);
    String body = objectMapper.writeValueAsString(dto);

    mvc.perform(post("/api/person-management/people")
        .contentType(MediaType.APPLICATION_JSON)
        .content(body))
      .andExpect(status().isCreated())
      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.firstName").value(firstName))
      .andExpect(jsonPath("$.lastName").value(lastName))
      .andExpect(jsonPath("$.dateOfBirth").value("1998-11-29"));
  }

  @Test
  @Order(2)
  public void deletePersonSuccessful() throws Exception {
    mvc.perform(delete("/api/person-management/people/1")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());
  }

  @Test
  @Order(3)
  public void checkPersonIsDeleted() throws Exception {
    mvc.perform(get("/api/person-management/people/1")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isNotFound())
      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.description").value("Person was not found"));
  }

  @Test
  @Order(4)
  public void deletePersonNonSuccessfully() throws Exception {
    mvc.perform(delete("/api/person-management/people/1")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isNotFound())
      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.description").value("Person was not found"));
  }
}
