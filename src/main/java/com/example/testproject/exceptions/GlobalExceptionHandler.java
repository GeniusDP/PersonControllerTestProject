package com.example.testproject.exceptions;

import com.example.testproject.dtos.errors.AppError;
import com.example.testproject.dtos.errors.ValidationExceptionResponse;
import com.example.testproject.dtos.errors.Violation;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(PersonNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public AppError personNotFoundExceptionHandler() {
    return AppError.justNow("Person was not found");
  }

  @ExceptionHandler(BindException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ValidationExceptionResponse argumentsNotValidExceptionHandle(BindException exception) {
    List<Violation> violations = exception.getAllErrors().stream()
      .map(objectError -> (FieldError) objectError)
      .map(objectError -> new Violation(objectError.getField(),
        objectError.getDefaultMessage()))
      .toList();
    return new ValidationExceptionResponse(violations);
  }

}
