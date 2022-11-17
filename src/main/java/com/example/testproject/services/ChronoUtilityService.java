package com.example.testproject.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.springframework.stereotype.Service;

@Service
public class ChronoUtilityService {

  public long getYearsDifference(LocalDate elderDate, LocalDate newerDate) {
    if (newerDate == null || elderDate == null) {
      throw new IllegalArgumentException("elderDate and newerDate must not be null");
    }
    if (newerDate.isBefore(elderDate)) {
      throw new IllegalArgumentException("elderDate must be elder than newerDate");
    }
    return ChronoUnit.YEARS.between(elderDate, newerDate);
  }

}
