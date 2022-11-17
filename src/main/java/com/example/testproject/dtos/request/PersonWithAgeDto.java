package com.example.testproject.dtos.request;

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

}
