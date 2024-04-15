package com.betrybe.agrix.solution;

import static  org.junit.jupiter.api.Assertions.*;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.security.Role;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Teste Person da camada de logica BD H2.")
public class PersonServiceTest {

  @Autowired
  PersonService personService;

  @Test
  @DisplayName("Deveria retornar um Person pelo id")
  public void getPersonByIdTest() {
    Person personToCreate = new Person();
    personToCreate.setUsername("test user 1");
    personToCreate.setPassword("test password");
    personToCreate.setRole(Role.USER);

    Person savedPerson = personService.create(personToCreate);
    Long savedPersonId = savedPerson.getId();

    Person returnedPerson = personService.getPersonById(savedPersonId);

    assertEquals(returnedPerson.getId(), savedPersonId);
    assertEquals(returnedPerson.getUsername(), personToCreate.getUsername());
    assertEquals(returnedPerson.getPassword(), personToCreate.getPassword());
    assertEquals(returnedPerson.getRole(), personToCreate.getRole());
  }

  @Test
  @DisplayName("Deveria lançar uma exceção caso não encontre um id fornecido")
  public void getPersonByIdThrowsTest() {
    assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(123L));
  }

  @Test
  @DisplayName("Deveria retornar um Person pelo Username")
  public void getPersonByUsernameTest() {
    Person personToCreate = new Person();
    personToCreate.setUsername("test user 2");
    personToCreate.setPassword("test password");
    personToCreate.setRole(Role.USER);

    Long savedPersonId = personService.create(personToCreate).getId();

    Person personByUsername = personService.getPersonByUsername(personToCreate.getUsername());

    assertEquals(personByUsername.getId(), savedPersonId);
    assertEquals(personByUsername.getUsername(), personToCreate.getUsername());
    assertEquals(personByUsername.getPassword(), personToCreate.getPassword());
    assertEquals(personByUsername.getRole(), personToCreate.getRole());
  }

  @Test
  @DisplayName("Deveria lançar uma exceção caso não encontre um nome fornecido")
  public void getPersonByUsernameThrowsTest() {
    assertThrows(PersonNotFoundException.class,
        () -> personService.getPersonByUsername("anything"));
  }

  @Test
  @DisplayName("Deveria criar um person corretamente")
  public void createPersonTest() {
    Person personToCreate = new Person();
    personToCreate.setUsername("test user 3");
    personToCreate.setPassword("test password");
    personToCreate.setRole(Role.USER);

    Long savedPersonId = personService.create(personToCreate).getId();

    Person returnedPerson = personService.getPersonById(savedPersonId);

    assertEquals(returnedPerson.getId(), savedPersonId);
    assertEquals(returnedPerson.getUsername(), personToCreate.getUsername());
    assertEquals(returnedPerson.getPassword(), personToCreate.getPassword());
    assertEquals(returnedPerson.getRole(), personToCreate.getRole());
  }
}
