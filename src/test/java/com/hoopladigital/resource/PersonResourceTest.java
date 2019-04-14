package com.hoopladigital.resource;

import com.hoopladigital.bean.Person;
import com.hoopladigital.bean.Pet;
import com.hoopladigital.service.PersonService;
import com.hoopladigital.service.PetService;
import com.hoopladigital.test.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.Response;

import static com.hoopladigital.test.MockHelper.allDeclaredMocks;
import static org.junit.Assert.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class PersonResourceTest extends AbstractTest {

	@Mock
	private PersonService personService;

	@Mock
	private PetService petService;

	private PersonResource personResource;

	@Before
	public void beforePersonResourceTest() {
		personResource = new PersonResource(personService, petService);
	}

	@Test
	public void should_get_person_list() {

		// setup
		final List<Person> expected = Collections.emptyList();
		when(personService.getPersonList()).thenReturn(expected);

		// run test
		final List<Person> actual = personResource.getPersonList();

		// verify mocks / capture values
		verify(personService).getPersonList();
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(expected, actual);

	}

	@Test
	public void should_get_person_by_id() {

		// setup
		final Long queriedId = 1L;
		final Person expected = new Person();
		when(personService.getPersonById(queriedId)).thenReturn(expected);

		// run test
		final Person actual = personResource.getPersonById(queriedId);

		// verify mocks / capture values
		verify(personService).getPersonById(queriedId);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(expected, actual);
	}

	@Test
	public void should_get_person_by_id_invalid_id() {

		// setup
		final Long queriedId = 1_000L;
		when(personService.getPersonById(queriedId)).thenReturn(null);

		// run test
		final Person actual = personResource.getPersonById(queriedId);

		// verify mocks / capture values
		verify(personService).getPersonById(queriedId);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertNull(actual);
	}

	@Test
	public void should_get_pet_list_by_person_id() {

		// setup
		final Long personId = 1L;
		when(petService.getPetListByPersonId(personId)).thenReturn(Collections.emptyList());

		// run test
		final List<Pet> actual = personResource.getPetListByPersonId(personId);

		// verify mocks / capture values
		verify(petService).getPetListByPersonId(personId);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertTrue(actual.isEmpty());
	}

	@Test
	public void should_create_person() throws Exception {

		// setup
		final Long expectedId = 25L;

		final Person provided = new Person();
		provided.setFirstName("Jean");
		provided.setMiddleName("Paul");
		provided.setLastName("Gaultier");

		final Person expected = new Person();
		expected.setFirstName(provided.getFirstName());
		expected.setMiddleName(provided.getMiddleName());
		expected.setLastName(provided.getLastName());
		expected.setId(expectedId);

		when(personService.createPerson(provided)).thenReturn(expected);

		// run test
		final Person actual = personResource.createPerson(provided);

		// verify mocks / capture values
		verify(personService).createPerson(provided);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(expectedId, actual.getId());
	}

	@Test
	public void should_update_person() {

		// setup
		final Person person = new Person();
		person.setId(1L);
		person.setFirstName("Jean");
		person.setMiddleName("Paul");
		person.setLastName("Gaultier");

		when(personService.updatePerson(person)).thenReturn(person);

		// run test
		final Person actual = personResource.updatePerson(person.getId(), person);

		// verify mocks / capture results
		verify(personService).updatePerson(person);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(person, actual);
	}

	@Test
	public void should_delete_person() {

		// setup
		final Long id = 5L;
		when(personService.deletePerson(any(Person.class))).thenReturn(true);

		// run test
		final Response response = personResource.deletePerson(id);

		// verify mocks / capture results
		verify(personService).deletePerson(any(Person.class)); // TODO: Improve this
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(204, response.getStatus());
	}

	@Test
	public void should_delete_person_invalid_request() {

		// setup
		final Long id = 5L;
		when(personService.deletePerson(any(Person.class))).thenReturn(false);

		// run test
		final Response response = personResource.deletePerson(id);

		// verify mocks / capture results
		verify(personService).deletePerson(any(Person.class)); // TODO: Improve this
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(404, response.getStatus());
	}

}
