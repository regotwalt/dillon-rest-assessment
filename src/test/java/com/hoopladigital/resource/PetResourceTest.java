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
}
