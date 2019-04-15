package com.hoopladigital.mapper;

import com.hoopladigital.bean.Person;
import com.hoopladigital.test.AbstractMapperTest;
import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import static org.junit.Assert.*;

public class PersonMapperTest extends AbstractMapperTest {

	@Inject
	private PersonMapper personMapper;

	@Inject
	private PetMapper petMapper;

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
		final Long queriedId = 6L;

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
		final Long queriedId = 1_000L;

		// run test
		final Person actual = personMapper.getPersonById(queriedId);

		// assert results
		assertNull(actual);
	}

	@Test
	public void should_create_person() {

		// setup
		final Long expectedId = 11L;
		final Person person = MapperTestHelper.createValidPerson(false);

		// run test
		final int rowsAdded = personMapper.createPerson(person);

		// assert results
		assertEquals(1, rowsAdded);
		assertEquals(expectedId, person.getId());
	}

	@Test(expected=PersistenceException.class)
	public void should_create_person_null_person() {

		// run test
		personMapper.createPerson(null);
	}

	@Test
	public void should_update_person() throws Exception {

		// setup
		final Person expected = MapperTestHelper.createValidPerson(true);

		// run test
		final int rowsUpdated = personMapper.updatePerson(expected);

		// verify mocks / capture values
		final Person actual = personMapper.getPersonById(expected.getId());

		// assert results
		assertEquals(1, rowsUpdated);
		beanTestHelper.diffBeans(expected, actual);
	}

	@Test(expected=PersistenceException.class)
	public void should_update_person_null_person() {

		// run test
		personMapper.updatePerson(null);
	}

	@Test(expected=PersistenceException.class)
	public void should_update_person_null_id() {

		// setup
		final Person person = MapperTestHelper.createValidPerson(true);
		person.setId(null);

		// run test
		personMapper.updatePerson(person);
	}

	@Test
	public void should_update_person_invalid_id() {

		// setup
		final Person person = MapperTestHelper.createValidPerson(true);
		person.setId(1_000L);

		// run test
		final int rowsUpdated = personMapper.updatePerson(person);

		// assert results
		assertEquals(0, rowsUpdated);
	}

	@Test
	public void should_delete_person() {

		// setup
		final Long personId = 3L;
		final int petCount = 33;
		final Long petId = 23L;

		// pre test asserts
		assertEquals(petCount, petMapper.getPetList().size());
		assertNotNull(petMapper.getPetById(petId));

		// run test
		final int rowsDeleted = personMapper.deletePerson(personId);

		// assert results
		assertEquals(1, rowsDeleted);
		assertEquals(9, personMapper.getPersonList().size());
		assertNull(personMapper.getPersonById(personId));

		assertEquals(petCount - 1, petMapper.getPetList().size());
		assertNull(petMapper.getPetById(petId));
	}

	@Test(expected=PersistenceException.class)
	public void should_delete_person_null_person_id() {

		// run test
		personMapper.deletePerson(null);
	}

	@Test
	public void should_delete_person_with_invalid_id() {

		// setup
		final Long personId = 1_000L;

		// run test
		final int rowsDeleted = personMapper.deletePerson(personId);

		// assert results
		assertEquals(0, rowsDeleted);
		assertEquals(10, personMapper.getPersonList().size());
		assertNull(personMapper.getPersonById(personId));
	}

}
