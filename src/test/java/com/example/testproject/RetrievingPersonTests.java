package com.example.testproject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.testproject.entities.Person;
import com.example.testproject.repositories.PersonRepository;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestProjectApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yaml")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class RetrievingPersonTests {

  @Autowired
  private MockMvc mvc;

  @Autowired
  PersonRepository repository;

  @BeforeEach
  public void init(){
    Person bogdan = Person.builder()
      .firstName("Bogdan")
      .lastName("Zaranik")
      .dateOfBirth(LocalDate.of(2002, Month.JULY, 24))
      .build();
    Person maxim = Person.builder()
      .firstName("Maxim")
      .lastName("Borisov")
      .dateOfBirth(LocalDate.of(2005, Month.MAY, 11))
      .build();
    repository.save(bogdan);
    repository.save(maxim);
  }

  @Test
  void getPersonById() throws Exception {
    mvc.perform(get("/api/person-management/people/1")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.firstName").value("Bogdan"))
      .andExpect(jsonPath("$.lastName").value("Zaranik"));
  }

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
  void noSuchPersonFound() throws Exception {
    mvc.perform(get("/api/person-management/people/-1000")
          .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isNotFound())
      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.description").value("Person was not found"));
  }


}
