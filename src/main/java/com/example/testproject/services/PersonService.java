package com.example.testproject.services;

import com.example.testproject.dtos.request.PersonDto;
import com.example.testproject.dtos.request.PersonWithAgeDto;
import com.example.testproject.entities.Person;
import com.example.testproject.exceptions.PersonNotFoundException;
import com.example.testproject.repositories.PersonRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  @Autowired
  private PersonRepository repository;

  public List<PersonWithAgeDto> getAll() {
    return repository.findAll().stream()
      .map(PersonWithAgeDto::new)
      .toList();
  }

  public PersonWithAgeDto getPersonById(Long personId) {
    Optional<Person> personOptional = repository.findById(personId);
    if (personOptional.isEmpty()) {
      throw new PersonNotFoundException();
    }
    Person person = personOptional.get();
    return new PersonWithAgeDto(person);
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
