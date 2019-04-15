package com.hoopladigital.resource;

import com.hoopladigital.bean.Person;
import com.hoopladigital.bean.Pet;
import com.hoopladigital.service.PersonService;
import com.hoopladigital.service.PetService;
import com.hoopladigital.test.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
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
		final Response response = personResource.getPersonById(queriedId);

		// verify mocks / capture values
		verify(personService).getPersonById(queriedId);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(200, response.getStatus());
		assertEquals(expected, response.getEntity());
	}

	@Test
	public void should_get_person_by_id_invalid_id() {

		// setup
		final Long queriedId = 1_000L;
		when(personService.getPersonById(queriedId)).thenReturn(null);

		// run test
		final Response response = personResource.getPersonById(queriedId);

		// verify mocks / capture values
		verify(personService).getPersonById(queriedId);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(404, response.getStatus());
		assertNull(response.getEntity());
	}

	@Test
	public void should_get_pet_list_by_person_id() {

		// setup
		final Long personId = 1L;
		final List<Pet> returned = new ArrayList<>();
		returned.add(new Pet());
		when(petService.getPetListByPersonId(personId)).thenReturn(returned);

		// run test
		final Response response = personResource.getPetListByPersonId(personId);

		// verify mocks / capture values
		verify(petService).getPetListByPersonId(personId);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(200, response.getStatus());
		assertEquals(returned, response.getEntity());
	}

	@Test
	public void should_get_pet_list_by_person_id_no_pets() {

		// setup
		final Long personId = 1L;
		final List<Pet> returned = Collections.emptyList();
		when(petService.getPetListByPersonId(personId)).thenReturn(returned);
		when(personService.getPersonById(personId)).thenReturn(new Person());

		// run test
		final Response response = personResource.getPetListByPersonId(personId);

		// verify mocks / capture values
		verify(petService).getPetListByPersonId(personId);
		verify(personService).getPersonById(personId);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(200, response.getStatus());
		assertEquals(returned, response.getEntity());
	}

	@Test
	public void should_get_pet_list_by_person_id_no_person() {

		// setup
		final Long personId = 1L;
		when(petService.getPetListByPersonId(personId)).thenReturn(Collections.emptyList());
		when(personService.getPersonById(personId)).thenReturn(null);

		// run test
		final Response response = personResource.getPetListByPersonId(personId);

		// verify mocks / capture values
		verify(petService).getPetListByPersonId(personId);
		verify(personService).getPersonById(personId);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(404, response.getStatus());
		assertNull(response.getEntity());
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
		final Response response = personResource.createPerson(provided);

		// verify mocks / capture values
		verify(personService).createPerson(provided);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(200, response.getStatus());
		assertEquals(expected, response.getEntity());
	}

	@Test
	public void should_create_person_invalid_request() throws Exception {

		// setup
		final Person provided = new Person();
		when(personService.createPerson(provided)).thenReturn(null);

		// run test
		final Response response = personResource.createPerson(provided);

		// verify mocks / capture values
		verify(personService).createPerson(provided);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(400, response.getStatus());
		assertNull(response.getEntity());
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
		final Response response = personResource.updatePerson(person.getId(), person);

		// verify mocks / capture results
		verify(personService).updatePerson(person);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(200, response.getStatus());
		assertEquals(person, response.getEntity());
	}

	@Test
	public void should_update_person_invalid_request() {

		// setup
		final Person person = new Person();
		person.setId(1L);
		person.setFirstName("Jean");
		person.setMiddleName("Paul");
		person.setLastName("Gaultier");

		when(personService.updatePerson(person)).thenReturn(null);

		// run test
		final Response response = personResource.updatePerson(person.getId(), person);

		// verify mocks / capture results
		verify(personService).updatePerson(person);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(404, response.getStatus());
		assertNull(response.getEntity());
	}

	@Test
	public void should_delete_person() {

		// setup
		final Long id = 5L;
		when(personService.deletePerson(id)).thenReturn(true);

		// run test
		final Response response = personResource.deletePerson(id);

		// verify mocks / capture results
		verify(personService).deletePerson(id);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(204, response.getStatus());
	}

	@Test
	public void should_delete_person_invalid_request() {

		// setup
		final Long id = 5L;
		when(personService.deletePerson(id)).thenReturn(false);

		// run test
		final Response response = personResource.deletePerson(id);

		// verify mocks / capture results
		verify(personService).deletePerson(id);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(404, response.getStatus());
	}

}
