package com.hoopladigital.resource;

import com.hoopladigital.bean.Pet;
import com.hoopladigital.service.PetService;
import com.hoopladigital.test.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static com.hoopladigital.test.MockHelper.allDeclaredMocks;
import static org.junit.Assert.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class PetResourceTest extends AbstractTest {

	@Mock
	private PetService petService;

	private PetResource petResource;

	@Before
	public void beforePetResourceTest() {
		petResource = new PetResource(petService);
	}

	@Test
	public void should_get_pet_list() {

		// setup
		final List<Pet> expected = Collections.emptyList();
		when(petService.getPetList()).thenReturn(expected);

		// run test
		final List<Pet> actual = petResource.getPetList();

		// verify mocks / capture values
		verify(petService).getPetList();
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(expected, actual);
	}

	@Test
	public void should_get_pet_by_id() {

		// setup
		final long queriedId = 1L;
		final Pet expected = new Pet();
		when(petService.getPetById(queriedId)).thenReturn(expected);

		// run test
		final Pet actual = petResource.getPetById(queriedId);

		// verify mocks / capture values
		verify(petService).getPetById(queriedId);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(expected, actual);
	}

	@Test
	public void should_get_pet_by_id_invalid_id() {

		// setup
		final long queriedId = 1_000L;
		when(petService.getPetById(queriedId)).thenReturn(null);

		// run test
		final Pet actual = petResource.getPetById(queriedId);

		// verify mocks / capture values
		verify(petService).getPetById(queriedId);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertNull(actual);
	}

	@Test
	public void should_create_pet() {

		// setup
		final Long expectedId = 100L;

		final Pet returned = new Pet();
		returned.setId(expectedId);
		returned.setPersonId(1L);
		returned.setName("Jack");

		when(petService.createPet(any(Pet.class))).thenReturn(returned);

		// run test
		final Pet actual = petResource.createPet(returned.getPersonId(), returned.getName());

		// verify mocks / capture values
		// TODO: Test the internals of the method w/o changing structure of PetResource (consistency)

		// assert results
		assertEquals(expectedId, actual.getId());
	}

	@Test
	public void should_update_pet() {

		// setup
		final Pet expected = new Pet();
		expected.setId(1L);
		expected.setPersonId(1L);
		expected.setName("Fuzzy Bear");

		when(petService.updatePet(any(Pet.class))).thenReturn(expected);

		// run test
		final Pet actual = petResource.updatePet(expected.getId(), expected.getPersonId(), expected.getName());

		// verify mocks / capture values
		// TODO: Test the internals of the methods w/o changing structure of PetResource

		// assert result
		assertEquals(expected, actual);
	}

	@Test
	public void should_delete_pet() {

		// setup
		final Pet pet = new Pet();
		pet.setId(5L);
		when(petService.deletePet(any(Pet.class))).thenReturn(true);

		// run test
		final boolean deleted = petResource.deletePet(pet.getId());

		// verify mocks / capture values
		// TODO: Test the internals of the methods w/o changing the structure of PersonResource

		// assert results
		assertTrue(deleted);
	}

}
