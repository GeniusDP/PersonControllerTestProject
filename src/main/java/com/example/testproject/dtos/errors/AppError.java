package com.example.testproject.dtos.errors;

import java.time.LocalDateTime;

public record AppError(String description, LocalDateTime causedTime) {

  public static AppError justNow(String message) {
    return new AppError(message, LocalDateTime.now());
  }
}
