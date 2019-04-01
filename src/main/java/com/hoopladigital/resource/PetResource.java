package com.hoopladigital.resource;

import com.hoopladigital.bean.Pet;
import com.hoopladigital.service.PetService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/pets")
public class PetResource {

	private final PetService petService;

	@Inject
	public PetResource(final PetService petService) {
		this.petService = petService;
	}

	@GET
	@Produces("application/json")
	public List<Pet> getPetList() {
		// TODO: Test endpoint
		return petService.getPetList();
	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public Pet getPetById(@PathParam("id") Long id) {
		// TODO: Deal with response code (404 if invalid id)
		// TODO: Test endpoint
		return petService.getPetById(id);
	}

}
