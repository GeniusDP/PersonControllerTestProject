package com.example.testproject.dtos.request;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class PersonDto {

  @NotNull(message = "firstName must not be null")
  @Size(min = 2, max = 50)
  private String firstName;

  @NotNull(message = "lastName must not be null")
  @Size(min = 2, max = 50)
  private String lastName;

  @Past(message = "date of birth must be in past time")
  @NotNull(message = "date of birth must not be null")
  private LocalDate dateOfBirth;

}
