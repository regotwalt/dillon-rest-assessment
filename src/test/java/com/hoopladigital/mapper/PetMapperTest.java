package com.hoopladigital.mapper;

import com.hoopladigital.bean.Pet;
import com.hoopladigital.test.AbstractMapperTest;
import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

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

}
