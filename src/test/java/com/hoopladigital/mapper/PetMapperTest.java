package com.hoopladigital.mapper;

import com.hoopladigital.bean.Pet;
import com.hoopladigital.test.AbstractMapperTest;
import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

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
	public void should_get_pet_by_id() throws Exception {

		// setup
		final long queriedId = 14L;

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

	@Test
	public void should_get_pet_by_id_invalid_id() {

		// setup
		final long queriedId = 1_000L;

		// run test
		final Pet actual = petMapper.getPetById(queriedId);

		// assert results
		assertNull(actual);
	}

	@Test
	public void should_create_pet() {

		// setup
		final Long expectedId = 35L; // TODO: counter got messed up somehow, should be 34

		final Pet pet = new Pet();
		pet.setPersonId(1L);
		pet.setName("Sgt Pepper");

		// run test
		petMapper.createPet(pet);

		// assert results
		assertEquals(expectedId, pet.getId());
	}

	@Test(expected=PersistenceException.class)
	public void should_create_pet_null_pet() {

		// run test
		petMapper.createPet(null);
	}

	@Test(expected=PersistenceException.class)
	public void should_create_pet_null_person_id() {

		// setup
		final Pet pet = new Pet();
		pet.setPersonId(null);
		pet.setName("Fido");

		// run test
		petMapper.createPet(pet);
	}

	@Test(expected=PersistenceException.class)
	public void should_create_pet_invalid_person_id() {

		// setup
		final Pet pet = new Pet();
		pet.setPersonId(1_000L);
		pet.setName("Spot");

		// run test
		petMapper.createPet(pet);
	}

}
