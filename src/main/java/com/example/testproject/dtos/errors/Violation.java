package com.example.testproject.dtos.errors;

import io.swagger.v3.oas.annotations.media.Schema;

public record Violation(@Schema(description = "field name") String fieldName,
                        @Schema(description = "message") String message) {

  public Violation(String fieldName, String message) {
    this.fieldName = fieldName;
    this.message = message;
  }

}