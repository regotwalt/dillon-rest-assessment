package com.hoopladigital.mapper;

import com.hoopladigital.bean.Pet;
import com.hoopladigital.test.AbstractMapperTest;
import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import static com.hoopladigital.mapper.MapperTestHelper.over50name;
import static org.junit.Assert.*;

public class PetMapperTest extends AbstractMapperTest {

	@Inject
	private PetMapper petMapper;

	@Test
	public void should_get_pet_list() throws Exception {

		// setup
		final Pet expectedFirst = new Pet();
		expectedFirst.setId(1L);
		expectedFirst.setPersonId(1L);
		expectedFirst.setName("Samson");

		// run test
		final List<Pet> petList = petMapper.getPetList();

		// assert results
		assertEquals(33, petList.size());
		beanTestHelper.diffBeans(expectedFirst, petList.get(0));
	}

	@Test
	public void should_get_pet_list_by_person_id() throws Exception {

		// setup
		final Long personId = 1L;
		final Pet expectedFirst = new Pet();
		expectedFirst.setId(1L);
		expectedFirst.setPersonId(personId);
		expectedFirst.setName("Samson");

		// run test
		final List<Pet> petList = petMapper.getPetListByPersonId(personId);

		// assert results
		assertEquals(18, petList.size());
		beanTestHelper.diffBeans(expectedFirst, petList.get(0));
	}

	@Test(expected=PersistenceException.class)
	public void should_get_pet_list_by_person_id_null_person_id() {

		// run test
		petMapper.getPetListByPersonId(null);
	}

	@Test
	public void should_get_pet_list_by_person_id_invalid_person_id() {

		// setup
		final Long invalidPersonId = 1_000L;

		// run test
		final List<Pet> petList = petMapper.getPetListByPersonId(invalidPersonId);

		// assert results
		assertEquals(0, petList.size());
	}

	@Test
	public void should_get_pet_by_id() throws Exception {

		// setup
		final Long queriedId = 14L;

		final Pet expected = new Pet();
		expected.setId(queriedId);
		expected.setPersonId(1L);
		expected.setName("Lady Rover");

		// run test
		final Pet actual = petMapper.getPetById(queriedId);

		// assert results
		assertNotNull(actual);
		beanTestHelper.diffBeans(expected, actual);
	}

	@Test(expected=PersistenceException.class)
	public void should_get_pet_by_id_null_id() {

		// run test
		final Pet actual = petMapper.getPetById(null);
	}

	@Test
	public void should_get_pet_by_id_invalid_id() {

		// setup
		final Long queriedId = 1_000L;

		// run test
		final Pet actual = petMapper.getPetById(queriedId);

		// assert results
		assertNull(actual);
	}

	@Test
	public void should_create_pet() {

		// setup
		final Long expectedId = 34L;
		final Pet pet = MapperTestHelper.createValidPet(false);

		// run test
		final int rowsAdded = petMapper.createPet(pet);

		// assert results
		assertEquals(1, rowsAdded);
		assertTrue(pet.getId() >= expectedId);
	}

	@Test(expected=PersistenceException.class)
	public void should_create_pet_null_pet() {

		// run test
		petMapper.createPet(null);
	}

	@Test(expected=PersistenceException.class)
	public void should_create_pet_null_person_id() {

		// setup
		final Pet pet = MapperTestHelper.createValidPet(false);
		pet.setPersonId(null);

		// run test
		petMapper.createPet(pet);
	}

	@Test(expected=PersistenceException.class)
	public void should_create_pet_invalid_person_id() {

		// setup
		final Pet pet = MapperTestHelper.createValidPet(false);
		pet.setPersonId(1_000L);

		// run test
		petMapper.createPet(pet);
	}

	@Test(expected=PersistenceException.class)
	public void should_create_pet_too_large_name() {

		// setup
		final Pet pet = MapperTestHelper.createValidPet(false);
		pet.setName(over50name);

		// run test
		petMapper.createPet(pet);
	}

	// TODO: Investigate MyBatis handling of nulls. This fails.
//	@Test
//	public void should_create_pet_null_name() {
//
//		// setup
//		final Pet pet = MapperTestHelper.createValidPet(false);
//		pet.setName(null);
//
//		// run test
//		final int rowsAdded = petMapper.createPet(pet);
//
//		// verify mocks / capture values
//		final Pet saved = petMapper.getPetById(pet.getId());
//
//		// assert results
//		assertEquals(1, rowsAdded);
//		assertNull(saved.getName());
//	}

	@Test
	public void should_update_pet() throws Exception {

		// setup - note that this pet's data differs from it's corresponding database entry
		final Pet expected = MapperTestHelper.createValidPet(true);

		// run test
		final int rowsUpdated = petMapper.updatePet(expected);

		// verify mocks / capture values
		final Pet actual = petMapper.getPetById(expected.getId());

		// assert results
		assertEquals(1, rowsUpdated);
		beanTestHelper.diffBeans(expected, actual);
	}

	@Test(expected=PersistenceException.class)
	public void should_update_pet_null_pet() {

		// run test
		petMapper.updatePet(null);
	}

	@Test(expected=PersistenceException.class)
	public void should_update_pet_null_id() {

		// setup
		final Pet pet = MapperTestHelper.createValidPet(true);
		pet.setId(null);

		// run test
		petMapper.updatePet(pet);
	}

	@Test
	public void should_update_pet_invalid_id() {

		// setup
		final Pet pet = MapperTestHelper.createValidPet(true);
		pet.setId(1_000L);

		// run test
		final int rowsUpdated = petMapper.updatePet(pet);

		// assert results
		assertEquals(0, rowsUpdated);
	}

	@Test(expected=PersistenceException.class)
	public void should_update_pet_null_person_id() {

		// setup
		final Pet pet = MapperTestHelper.createValidPet(true);
		pet.setPersonId(null);

		// run test
		petMapper.updatePet(pet);
	}

	@Test(expected=PersistenceException.class)
	public void should_update_pet_invalid_person_id() {

		// setup
		final Pet pet = MapperTestHelper.createValidPet(true);
		pet.setPersonId(1_000L);

		// run test
		petMapper.updatePet(pet);
	}

	@Test(expected=PersistenceException.class)
	public void should_update_pet_too_large_name() {

		// setup
		final Pet pet = MapperTestHelper.createValidPet(true);
		pet.setName(over50name);

		// run test
		petMapper.createPet(pet);
	}

	// TODO: Investigate MyBatis handling of nulls. This fails.
//	@Test
//	public void should_update_pet_null_name() throws Exception {
//
//		// setup
//		final Pet expected = MapperTestHelper.createValidPet(true);
//		expected.setName(null);
//
//		// run test
//		final int rowsUpdated = petMapper.updatePet(expected);
//
//		// verify mocks / capture values
//		final Pet actual = petMapper.getPetById(expected.getId());
//
//		// assert results
//		assertEquals(1, rowsUpdated);
//		assertNull(actual.getName());
//	}

	@Test
	public void should_delete_pet() {

		// setup
		final Long id = 10L;

		// run test
		final int rowsDeleted = petMapper.deletePet(id);

		// assert results
		assertEquals(1, rowsDeleted);
		assertEquals(32, petMapper.getPetList().size());
		assertNull(petMapper.getPetById(id));

	}

	@Test(expected=PersistenceException.class)
	public void should_delete_pet_null_pet_id() {

		// run test
		petMapper.deletePet(null);
	}

	@Test
	public void should_delete_pet_with_invalid_id() {

		// setup
		final Long id = 1_000L;

		// run test
		final int rowsDeleted = petMapper.deletePet(id);

		// assert results
		assertEquals(0, rowsDeleted);
		assertEquals(33, petMapper.getPetList().size());
	}

}
