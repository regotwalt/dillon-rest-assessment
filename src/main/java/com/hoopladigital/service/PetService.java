package com.hoopladigital.service;

import com.hoopladigital.bean.Pet;
import com.hoopladigital.mapper.PetMapper;

import javax.inject.Inject;
import java.util.List;

public class PetService {

	private final PetMapper petMapper;

	@Inject
	public PetService(final PetMapper petMapper) {
		this.petMapper = petMapper;
	}

	public List<Pet> getPetList() {
		return petMapper.getPetList();
	}
}
