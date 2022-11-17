package com.example.testproject.controllers;

import static com.example.testproject.Constants.Informer.HTTP_STATUS_CREATED;
import static com.example.testproject.Constants.Informer.HTTP_STATUS_NOT_FOUND;
import static com.example.testproject.Constants.Informer.HTTP_STATUS_NO_CONTENT;
import static com.example.testproject.Constants.Informer.HTTP_STATUS_OK;
import static com.example.testproject.Constants.PersonController.*;

import com.example.testproject.dtos.request.PersonDto;
import com.example.testproject.dtos.request.PersonWithAgeDto;
import com.example.testproject.entities.Person;
import com.example.testproject.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/person-management/people")
@Tag(name = TAG, description = TAG_DESCRIPTION)
@RequiredArgsConstructor
public class PersonController {

  private final PersonService personService;

  @GetMapping({"/", ""})
  @Operation(summary = GET_ALL_PEOPLE, description = GET_ALL_PEOPLE_DESCRIPTION)
  @ApiResponse(responseCode = HTTP_STATUS_OK, description = RETRIEVE_PEOPLE_LIST)
  public List<PersonWithAgeDto> getAllPeople(){
    return personService.getAll();
  }

  @GetMapping("/{personId}")
  @Operation(summary = GET_PERSON_BY_ID_SUMMARY, description = GET_PERSON_BY_ID_DESCRIPTION)
  @ApiResponse(responseCode = HTTP_STATUS_OK, description = RETRIEVE_PERSON_BY_ID)
  @ApiResponse(responseCode = HTTP_STATUS_NOT_FOUND, description = PERSON_NOT_FOUND)
  public PersonWithAgeDto getPersonById(@PathVariable Long personId){
    return personService.getPersonById(personId);
  }

  @PostMapping({"/", ""})
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = CREATE_PERSON_SUMMARY, description = CREATE_PERSON_DESCRIPTION)
  @ApiResponse(responseCode = HTTP_STATUS_CREATED, description = PERSON_SUCCESSFULLY_CREATED)
  public Person createPerson(@Valid @RequestBody PersonDto dto){
    return personService.createPerson(dto);
  }

  @DeleteMapping("/{personId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(summary = DELETE_PERSON_BY_ID_SUMMARY, description = DELETE_PERSON_BY_ID_DESCRIPTION)
  @ApiResponse(responseCode = HTTP_STATUS_NO_CONTENT, description = PERSON_SUCCESSFULLY_DELETED)
  @ApiResponse(responseCode = HTTP_STATUS_NOT_FOUND, description = PERSON_NOT_FOUND)
  public void deletePersonById(@PathVariable Long personId){
    personService.deletePersonById(personId);
  }

}
