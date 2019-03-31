package com.hoopladigital.resource;

import com.hoopladigital.bean.Person;
import com.hoopladigital.service.PersonService;
import com.hoopladigital.test.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static com.hoopladigital.test.MockHelper.allDeclaredMocks;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class PersonResourceTest extends AbstractTest {

	@Mock
	private PersonService personService;

	private PersonResource personResource;

	@Before
	public void beforePersonResourceTest() {
		personResource = new PersonResource(personService);
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
		final long queriedId = 1L;
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
		final long queriedId = 1_000L;
		when(personService.getPersonById(queriedId)).thenReturn(null);

		// run test
		final Person actual = personResource.getPersonById(queriedId);

		// verify mocks / capture values
		verify(personService).getPersonById(queriedId);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertNull(actual);
	}

}
