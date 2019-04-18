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
		if (personId == null) return Collections.emptyList();
		try {
			return petMapper.getPetListByPersonId(personId);
		}
		catch (PersistenceException pe) {
			// Improvement: Log error
			return Collections.emptyList();
		}
	}

	public Pet getPetById(final Long id) {
		if (id == null) return null;
		try {
			return petMapper.getPetById(id);
		}
		catch (PersistenceException pe) {
			// Improvement: Log error
			return null;
		}
	}

	public Pet createPet(final Pet pet) {
		if (pet == null) return null;
		try {
			int rowsCreated = petMapper.createPet(pet);
			// Future - log if rowsCreated is not 0 or 1 (unexpected)
			if (rowsCreated == 0) return null;
			return pet;
		}
		catch (PersistenceException pe) {
			// Improvement: Log error
			return null;
		}
	}

	public Pet updatePet(final Pet pet) {
		if (pet == null) return null;
		try {
			final int rowsUpdated = petMapper.updatePet(pet);
			// Future - log if rowsUpdated is not 0 or 1 (unexpected)
			if (rowsUpdated == 0) return null;
			return pet;
		}
		catch (PersistenceException pe) {
			// Improvement: Log error
			return null;
		}
	}

	public boolean deletePet(final Long id) {
		if (id == null) return false;
		try {
			final int numberDeleted = petMapper.deletePet(id);
			// Future - log if numberDeleted is not 0 or 1 (unexpected)
			return numberDeleted > 0;
		}
		catch (PersistenceException pe) {
			// Improvement: Log error
			return false;
		}
	}

}
