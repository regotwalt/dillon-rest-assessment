package com.hoopladigital.mapper;

import com.hoopladigital.bean.Pet;

import java.util.List;

public interface PetMapper {
	List<Pet> getPetList();
	List<Pet> getPetListByPersonId(final Long personId);
	Pet getPetById(final Long id);
	int createPet(final Pet pet);
	int updatePet(final Pet pet);
	int deletePet(final Long id);
}
