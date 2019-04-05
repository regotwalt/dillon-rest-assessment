package com.hoopladigital.resource;

import com.hoopladigital.bean.Pet;
import com.hoopladigital.service.PetService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

	@POST
	@Produces("application/json")
	public Pet createPet(@PathParam("personId") Long personId, @PathParam("name") String name) {
		// TODO: Test endpoint
		// TODO: MISSING REQUIREMENTS: Should any validation be done on name?

		final Pet pet = new Pet();
		pet.setPersonId(personId);
		pet.setName(name);

		return petService.createPet(pet);
	}

	@PUT
	@Produces("application/json")
	public Pet updatePet(@PathParam("id") Long id,
						 @PathParam("personId") Long personId,
						 @PathParam("name") String name) {

		// TODO: Test endpoint
		// TODO: MISSING REQUIREMENT: Should any validation be done on name?
		// TODO: Another option - make fields optional, update only what's provided

		final Pet pet = new Pet();
		pet.setId(id);
		pet.setPersonId(personId);
		pet.setName(name);

		return petService.updatePet(pet);
	}

}
