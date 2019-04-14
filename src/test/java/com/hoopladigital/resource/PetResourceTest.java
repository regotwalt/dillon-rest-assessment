package com.hoopladigital.resource;

import com.hoopladigital.bean.Pet;
import com.hoopladigital.service.PetService;
import com.hoopladigital.test.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.Response;

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
		final Long queriedId = 1L;
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
		final Long queriedId = 1_000L;
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
		final Long expectedId = 25L;

		final Pet provided = new Pet();
		provided.setName("Jack");
		provided.setPersonId(1L);

		final Pet expected = new Pet();
		expected.setName(provided.getName());
		expected.setPersonId(provided.getPersonId());
		expected.setId(expectedId);

		when(petService.createPet(provided)).thenReturn(expected);

		// run test
		final Pet actual = petResource.createPet(provided);

		// verify mocks / capture values
		verify(petService).createPet(provided);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(expectedId, actual.getId());
	}

	@Test
	public void should_update_pet() {

		// setup
		final Pet pet = new Pet();
		pet.setId(1L);
		pet.setName("Fuzzy Bear");
		pet.setPersonId(1L);

		when(petService.updatePet(pet)).thenReturn(pet);

		// run test
		final Pet actual = petResource.updatePet(pet.getId(), pet);

		// verify mocks / capture values
		verify(petService).updatePet(pet);
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert result
		assertEquals(pet, actual);
	}

	@Test
	public void should_delete_pet() {

		// setup
		final Long id = 5L;
		when(petService.deletePet(any(Pet.class))).thenReturn(true);

		// run test
		final Response response = petResource.deletePet(id);

		// verify mocks / capture values
		verify(petService).deletePet(any(Pet.class)); // TODO: Improve this
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(204, response.getStatus());
	}

	@Test
	public void should_delete_pet_invalid_request() {

		// setup
		final Long id = 5L;
		when(petService.deletePet(any(Pet.class))).thenReturn(false);

		// run test
		final Response response = petResource.deletePet(id);

		// verify mocks / capture values
		verify(petService).deletePet(any(Pet.class)); // TODO: Improve this
		verifyNoMoreInteractions(allDeclaredMocks(this));

		// assert results
		assertEquals(404, response.getStatus());
	}

}
