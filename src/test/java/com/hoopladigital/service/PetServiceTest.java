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
	public void should_get_pet_list_by_person_id() {

		// setup
		final Long personId = 100L;
		final List<Pet> expected = Collections.emptyList();
		when(petMapper.getPetListByPersonId(personId)).thenReturn(expected);

		// run test
		final List<Pet> actual = petService.getPetListByPersonId(personId);

		// verify mocks / capture values
		verify(petMapper).getPetListByPersonId(personId);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(expected, actual);
	}

	@Test
	public void should_get_pet_list_by_person_id_null_person_id() {

		// run test
		final List<Pet> actual = petService.getPetListByPersonId(null);

		// verify mocks / capture values
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertTrue(actual.isEmpty());
	}

	@Test
	public void should_get_pet_list_by_person_id_exception() {

		// setup
		final Long personId = 100L;
		doThrow(new PersistenceException()).when(petMapper).getPetListByPersonId(personId);

		// run test
		final List<Pet> actual = petService.getPetListByPersonId(personId);

		// verify mocks / capture values
		verify(petMapper).getPetListByPersonId(personId);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertTrue(actual.isEmpty());
	}

	@Test
	public void should_get_pet_by_id() {

		// setup
		final Long queriedId = 1L;
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
	public void should_get_pet_by_id_null_id() {

		// run test
		final Pet actual = petService.getPetById(null);

		// verify mocks / capture values
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertNull(actual);
	}

	@Test
	public void should_get_pet_by_id_exception() {

		// setup
		final Long queriedId = 1L;
		doThrow(new PersistenceException()).when(petMapper).getPetById(queriedId);

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
		when(petMapper.createPet(pet)).thenReturn(1);

		// run test
		final Pet actual = petService.createPet(pet);

		// verify mocks / capture values
		verify(petMapper).createPet(pet);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(pet, actual);
	}

	@Test
	public void should_create_pet_no_creation() {

		// setup
		final Pet pet = new Pet();
		when(petMapper.createPet(pet)).thenReturn(0);

		// run test
		final Pet actual = petService.createPet(pet);

		// verify mocks / capture values
		verify(petMapper).createPet(pet);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertNull(actual);
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
	public void should_create_pet_exception() {

		// setup
		final Pet pet = new Pet();
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
	public void should_update_pet() {

		// setup
		final Pet expected = new Pet();
		when(petMapper.updatePet(expected)).thenReturn(1);

		// run test
		final Pet actual = petService.updatePet(expected);

		// verify mocks / capture values
		verify(petMapper).updatePet(expected);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(expected, actual);
	}

	@Test
	public void should_update_pet_no_updation() {

		// setup
		final Pet expected = new Pet();
		when(petMapper.updatePet(expected)).thenReturn(0);

		// run test
		final Pet actual = petService.updatePet(expected);

		// verify mocks / capture values
		verify(petMapper).updatePet(expected);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertNull(actual);
	}

	@Test
	public void should_update_pet_null_pet() {

		// run test
		final Pet actual = petService.updatePet(null);

		// verify mocks / capture values
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertNull(actual);
	}

	@Test
	public void should_update_pet_exception() {

		// setup
		final Pet pet = new Pet();
		doThrow(new PersistenceException()).when(petMapper).updatePet(pet);

		// run test
		final Pet actual = petService.updatePet(pet);

		// verify mocks / capture values
		verify(petMapper).updatePet(pet);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertNull(actual);
	}

	@Test
	public void should_delete_pet() {

		// setup
		final Long id = 1L;
		when(petMapper.deletePet(id)).thenReturn(1);

		// run test
		final boolean deleted = petService.deletePet(id);

		// verify mocks / capture values
		verify(petMapper).deletePet(id);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertTrue(deleted);
	}

	@Test
	public void should_delete_pet_no_deletion() {

		// setup
		final Long id = 1L;
		when(petMapper.deletePet(id)).thenReturn(0);

		// run test
		final boolean deleted = petService.deletePet(id);

		// verify mocks / capture values
		verify(petMapper).deletePet(id);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertFalse(deleted);
	}

	@Test
	public void should_delete_pet_null_pet() {

		// run test
		final boolean deleted = petService.deletePet(null);

		// verify mocks / capture values
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertFalse(deleted);
	}

	@Test
	public void should_delete_pet_exception() {

		// setup
		final Long id = 1_000L;
		doThrow(new PersistenceException()).when(petMapper).deletePet(id);

		// run test
		final boolean deleted = petService.deletePet(id);

		// verify mocks / capture values
		verify(petMapper).deletePet(id);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertFalse(deleted);
	}

}
