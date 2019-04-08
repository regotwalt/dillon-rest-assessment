package com.hoopladigital.mapper;

import com.hoopladigital.bean.Pet;

import java.util.List;

public interface PetMapper {
	List<Pet> getPetList();
	Pet getPetById(Long id);
	int createPet(Pet pet);
	int updatePet(Pet pet);
	int deletePet(Pet pet);
}
