package com.example.testproject.services;

import com.example.testproject.dtos.request.PersonDto;
import com.example.testproject.dtos.request.PersonWithAgeDto;
import com.example.testproject.entities.Person;
import com.example.testproject.exceptions.PersonNotFoundException;
import com.example.testproject.repositories.PersonRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

  private final PersonRepository repository;
  private final ChronoUtilityService chronoUtilityService;

  public List<PersonWithAgeDto> getAll() {
    return repository.findAll().stream()
      .map(person -> {
        long ageInYears = calcPersonAge(person.getDateOfBirth());
        String firstName = person.getFirstName();
        String lastName = person.getLastName();
        return new PersonWithAgeDto(firstName, lastName, ageInYears);
      })
      .toList();
  }

  private long calcPersonAge(LocalDate dateOfBirth){
    LocalDate now = LocalDate.now();
    return chronoUtilityService.getYearsDifference(dateOfBirth, now);
  }

  public PersonWithAgeDto getPersonById(Long personId) {
    Optional<Person> personOptional = repository.findById(personId);
    if (personOptional.isEmpty()) {
      throw new PersonNotFoundException();
    }
    Person person = personOptional.get();
    long ageInYears = calcPersonAge(person.getDateOfBirth());
    String firstName = person.getFirstName();
    String lastName = person.getLastName();
    return new PersonWithAgeDto(firstName, lastName, ageInYears);
  }

  public Person createPerson(PersonDto dto) {
    Person person = Person.builder()
      .firstName(dto.getFirstName())
      .lastName(dto.getLastName())
      .dateOfBirth(dto.getDateOfBirth())
      .build();
    return repository.save(person);
  }

  public void deletePersonById(Long personId) {
    if (!repository.existsById(personId)) {
      throw new PersonNotFoundException();
    }
    repository.deleteById(personId);
  }
}
