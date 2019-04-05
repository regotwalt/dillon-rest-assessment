package com.hoopladigital.mapper;

import com.hoopladigital.bean.Pet;

public class MapperTestHelper {

	public static Pet createValidPet(boolean withId) {
		Pet pet = new Pet();
		pet.setPersonId(1L);
		pet.setName("Scruffy");
		if (withId) pet.setId(1L);
		return pet;
	}

}
