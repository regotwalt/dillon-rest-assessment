package com.hoopladigital.mapper;

import com.hoopladigital.bean.Person;
import com.hoopladigital.test.AbstractMapperTest;
import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

public class PersonMapperTest extends AbstractMapperTest {

	@Inject
	private PersonMapper personMapper;

	@Test
	public void should_get_person_list() throws Exception {

		// setup
		final Person george = new Person();
		george.setId(1L);
		george.setFirstName("George");
		george.setLastName("Washington");

		// run test
		final List<Person> personList = personMapper.getPersonList();

		// verify mocks / capture values

		// assert results
		assertEquals(10, personList.size());
		beanTestHelper.diffBeans(george, personList.get(0));

	}

	@Test
	public void should_get_person_by_id() throws Exception {

		// setup
		final long queriedId = 6L;

		final Person expected = new Person();
		expected.setId(queriedId);
		expected.setFirstName("John");
		expected.setMiddleName("Quincy");
		expected.setLastName("Adams");

		// run test
		final Person actual = personMapper.getPersonById(queriedId);

		// assert results
		assertNotNull(actual);
		beanTestHelper.diffBeans(expected, actual);
	}

	@Test
	public void should_get_person_by_id_invalid_id() {

		// setup
		final long queriedId = 1_000L;

		// run test
		final Person actual = personMapper.getPersonById(queriedId);

		// assert results
		assertNull(actual);
	}

}
