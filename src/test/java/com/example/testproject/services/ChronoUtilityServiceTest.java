package com.example.testproject.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

class ChronoUtilityServiceTest {

  private ChronoUtilityService service;

  @BeforeEach
  public void init(){
    service = new ChronoUtilityService();
  }

  @ParameterizedTest
  @MethodSource("getRegularTestCases")
  void getYearsDifferenceRegularCases(LocalDate elderDate, LocalDate newerDate, long expectedValue) {
    long providedValue = service.getYearsDifference(elderDate, newerDate);
    Assertions.assertThat(providedValue).isEqualTo(expectedValue);
  }

  @ParameterizedTest
  @NullSource
  void getYearsDifferenceNullCase(LocalDate elderDate) {
    LocalDate newerDate = LocalDate.now();
    assertThrows(IllegalArgumentException.class, ()->service.getYearsDifference(elderDate, newerDate));
  }

  @ParameterizedTest
  @MethodSource("getFailingTestCases")
  void getYearsDifferenceFailingTestCases(LocalDate elderDate, LocalDate newerDate) {
    assertThrows(IllegalArgumentException.class, ()->service.getYearsDifference(elderDate, newerDate));
  }

  private static Stream<Arguments> getRegularTestCases(){
    return Stream.of(
      Arguments.of(LocalDate.of(2002, Month.JULY, 24), LocalDate.now(), 20L),
      Arguments.of(LocalDate.now(), LocalDate.now(), 0L),
      Arguments.of(LocalDate.of(2005, Month.MAY, 11), LocalDate.now(), 17L)
    );
  }

  private static Stream<Arguments> getFailingTestCases(){
    return Stream.of(
      Arguments.of(LocalDate.now(), LocalDate.of(2002, Month.JULY, 24)),
      Arguments.of(LocalDate.now(), LocalDate.of(2005, Month.MAY, 11))
    );
  }

}