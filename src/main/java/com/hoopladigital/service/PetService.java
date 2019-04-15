package com.hoopladigital.service;

import com.hoopladigital.bean.Pet;
import com.hoopladigital.mapper.PetMapper;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

public class PetService {

	private final PetMapper petMapper;

	@Inject
	public PetService(final PetMapper petMapper) {
		this.petMapper = petMapper;
	}

	public List<Pet> getPetList() {
		return petMapper.getPetList();
	}

	public List<Pet> getPetListByPersonId(final Long personId) {
		try {
			return petMapper.getPetListByPersonId(personId);
		}
		catch (PersistenceException pe) {
			return Collections.emptyList();
		}
	}

	public Pet getPetById(final Long id) {
		return petMapper.getPetById(id);
	}

	public Pet createPet(final Pet pet) {
		if (pet == null) return null;
		try {
			petMapper.createPet(pet);
		}
		catch (PersistenceException pe) {
			return null;
		}
		return pet;
	}

	public Pet updatePet(final Pet pet) {
		if (pet == null) return null;
		try {
			final int rowsUpdated = petMapper.updatePet(pet);
			if (rowsUpdated == 0) return null;
		}
		catch (PersistenceException pe) {
			return null;
		}
		return pet;
	}

	public boolean deletePet(final Long id) {
		if (id == null) return false;
		try {
			final int numberDeleted = petMapper.deletePet(id);
			// Future: Log WARN if numberDeleted>1 - would be unexpected
			return numberDeleted > 0;
		}
		catch (PersistenceException pe) {
			return false;
		}
	}

}
