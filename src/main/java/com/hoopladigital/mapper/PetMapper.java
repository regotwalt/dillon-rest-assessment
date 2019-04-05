package com.hoopladigital.mapper;

import com.hoopladigital.bean.Pet;

import java.util.List;

public interface PetMapper {

	List<Pet> getPetList();
	Pet getPetById(Long id);
	void createPet(Pet pet);
	void updatePet(Pet pet);

}
