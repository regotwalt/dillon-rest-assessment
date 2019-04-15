package com.hoopladigital.service;

import com.hoopladigital.bean.Person;
import com.hoopladigital.mapper.PersonMapper;
import com.hoopladigital.test.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import static com.hoopladigital.test.MockHelper.allDeclaredMocks;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class PersonServiceTest extends AbstractTest {

	@Mock
	private PersonMapper personMapper;

	private PersonService personService;

	@Before
	public void beforePersonServiceTest() {
		personService = new PersonService(personMapper);
	}

	@Test
	public void should_get_person_list() {

		// setup
		final List<Person> expected = Collections.emptyList();
		when(personMapper.getPersonList()).thenReturn(expected);

		// run test
		final List<Person> actual = personService.getPersonList();

		// verify mocks / capture values
		verify(personMapper).getPersonList();
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(expected, actual);

	}

	@Test
	public void should_get_person_by_id() {

		// setup
		final Long queriedId = 1L;
		final Person expected = new Person();
		when(personMapper.getPersonById(queriedId)).thenReturn(expected);

		// run test
		final Person actual = personService.getPersonById(queriedId);

		// verify mocks / capture values
		verify(personMapper).getPersonById(queriedId);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(expected, actual);
	}

	@Test
	public void should_get_person_by_id_invalid_id() {

		// setup
		final Long queriedId = 1_000L;
		when(personMapper.getPersonById(queriedId)).thenReturn(null);

		// run test
		final Person actual = personService.getPersonById(queriedId);

		// verify mocks / capture values
		verify(personMapper).getPersonById(queriedId);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertNull(actual);
	}

	@Test
	public void should_create_person() {

		// setup
		final Person person = new Person();
		when(personMapper.createPerson(person)).thenReturn(1);

		// run test
		final Person actual = personService.createPerson(person);

		// verify mocks / capture values
		verify(personMapper).createPerson(person);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertNotNull(actual);
	}

	@Test
	public void should_create_person_null_person() {

		// run test
		final Person actual = personService.createPerson(null);

		// verify mocks / capture values
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertNull(actual);
	}

	@Test
	public void should_create_person_invalid_person() {

		// setup
		final Person person = new Person();
		doThrow(new PersistenceException()).when(personMapper).createPerson(person);

		// run test
		final Person actual = personService.createPerson(person);

		// verify mocks / capture values
		verify(personMapper).createPerson(person);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertNull(actual);
	}

	@Test
	public void should_update_person() {

		// setup
		final Person expected = new Person();
		when(personMapper.updatePerson(expected)).thenReturn(1);

		// run test
		final Person actual = personService.updatePerson(expected);

		// verify mocks / capture values
		verify(personMapper).updatePerson(expected);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(expected, actual);
	}

	@Test
	public void should_update_person_null_person() {

		// run test
		final Person actual = personService.updatePerson(null);

		// verify mocks / capture values
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertNull(actual);
	}

	@Test
	public void should_update_person_invalid_person() {

		// setup
		final Person person = new Person();
		doThrow(new PersistenceException()).when(personMapper).updatePerson(person);

		// run test
		final Person actual = personService.updatePerson(person);

		// verify mocks / capture values
		verify(personMapper).updatePerson(person);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertNull(actual);
	}

	@Test
	public void should_delete_person() {

		// setup
		final Long id = 1L;
		when(personMapper.deletePerson(id)).thenReturn(1);

		// run test
		final boolean deleted = personService.deletePerson(id);

		// verify mocks / capture values
		verify(personMapper).deletePerson(id);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertTrue(deleted);
	}

	@Test
	public void should_delete_person_no_deletion() {

		// setup
		final Long id = 1L;
		when(personMapper.deletePerson(id)).thenReturn(0);

		// run test
		final boolean deleted = personService.deletePerson(id);

		// verify mocks / capture values
		verify(personMapper).deletePerson(id);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertFalse(deleted);
	}

	@Test
	public void should_delete_person_null_person() {

		// run test
		final boolean deleted = personService.deletePerson(null);

		// verify mocks / capture values
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertFalse(deleted);
	}

	@Test
	public void should_delete_person_invalid_person() {

		// setup
		final Long id = 1L;
		doThrow(new PersistenceException()).when(personMapper).deletePerson(id);

		// run test
		final boolean deleted = personService.deletePerson(id);

		// verify mocks / capture values
		verify(personMapper).deletePerson(id);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertFalse(deleted);
	}

}
