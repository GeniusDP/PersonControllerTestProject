package com.example.testproject.dtos.request;

import com.example.testproject.entities.Person;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PersonWithAgeDto {

  private String firstName;

  private String lastName;

  private Long ageInYears;

  public PersonWithAgeDto(Person person) {
    this.firstName = person.getFirstName();
    this.lastName = person.getLastName();
    this.ageInYears = ChronoUnit.YEARS.between(person.getDateOfBirth(), LocalDate.now());
  }

}
