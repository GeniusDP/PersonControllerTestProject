package com.example.testproject.dtos.errors;

import java.util.List;
import lombok.Data;

@Data
public class ValidationExceptionResponse {

  private List<Violation> violations;

  public ValidationExceptionResponse(List<Violation> violations) {
    this.violations = violations;
  }

}