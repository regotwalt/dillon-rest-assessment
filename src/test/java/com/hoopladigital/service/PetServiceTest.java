package com.hoopladigital.service;

import com.hoopladigital.bean.Pet;
import com.hoopladigital.mapper.PetMapper;
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

public class PetServiceTest extends AbstractTest {

	@Mock
	private PetMapper petMapper;
	private PetService petService;

	@Before
	public void beforePetServiceTest() {
		petService = new PetService(petMapper);
	}

	@Test
	public void should_get_pet_list() {

		// setup
		final List<Pet> expected = Collections.emptyList();
		when(petMapper.getPetList()).thenReturn(expected);

		// run test
		final List<Pet> actual = petService.getPetList();

		// verify mocks / capture values
		verify(petMapper).getPetList();
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(expected, actual);
	}

	@Test
	public void should_get_pet_by_id() {

		// setup
		final long queriedId = 1L;
		final Pet expected = new Pet();
		when(petMapper.getPetById(queriedId)).thenReturn(expected);

		// run test
		final Pet actual = petService.getPetById(queriedId);

		// verify mocks / capture values
		verify(petMapper).getPetById(queriedId);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(expected, actual);
	}

	@Test
	public void should_get_pet_by_id_invalid_id() {

		// setup
		final long queriedId = 1_000L;
		when(petMapper.getPetById(queriedId)).thenReturn(null);

		// run test
		final Pet actual = petService.getPetById(queriedId);

		// verify mocks / capture values
		verify(petMapper).getPetById(queriedId);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertNull(actual);
	}

	@Test
	public void should_create_pet() {

		// setup
		final Pet pet = new Pet();
		doNothing().when(petMapper).createPet(pet);

		// run test
		final Pet actual = petService.createPet(pet);

		// verify mocks / capture values
		verify(petMapper).createPet(pet);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertNotNull(actual);
	}

	@Test
	public void should_create_pet_null_pet() {

		// run test
		final Pet actual = petService.createPet(null);

		// verify mocks / capture values
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertNull(actual);
	}

	@Test
	public void should_create_pet_null_person_id() {

		// setup
		final Pet pet = new Pet();
		pet.setPersonId(null);

		doThrow(new PersistenceException()).when(petMapper).createPet(pet);

		// run test
		final Pet actual = petService.createPet(pet);

		// verify mocks / capture values
		verify(petMapper).createPet(pet);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertNull(actual);
	}

	@Test
	public void should_create_pet_invalid_person_id() {

		// setup
		final Pet pet = new Pet();
		pet.setPersonId(1_000L);

		doThrow(new PersistenceException()).when(petMapper).createPet(pet);

		// run test
		final Pet actual = petService.createPet(pet);

		// verify mocks / capture values
		verify(petMapper).createPet(pet);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertNull(actual);
	}

}
