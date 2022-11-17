package com.example.testproject.entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "people")
@AllArgsConstructor
@NoArgsConstructor
public class Person extends BasicEntity {

  private String firstName;

  private String lastName;

  private LocalDate dateOfBirth;

}
