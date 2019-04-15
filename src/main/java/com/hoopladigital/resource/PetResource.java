package com.hoopladigital.resource;

import com.hoopladigital.bean.Pet;
import com.hoopladigital.service.PetService;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
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
		return petService.getPetList();
	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response getPetById(@PathParam("id") final Long id) {
		final Pet pet = petService.getPetById(id);
		return Response.status(pet == null ? Response.Status.NOT_FOUND : Response.Status.OK).entity(pet).build();
	}

	@POST
	@Produces("application/json")
	public Response createPet(final Pet pet) {
		// TODO: MISSING REQUIREMENTS: Should any validation be done on name?
		final Pet created = petService.createPet(pet);
		return Response.status(created == null ? Response.Status.BAD_REQUEST : Response.Status.OK).entity(created).build();
	}

	@PUT
	@Path("{id}")
	@Produces("application/json")
	public Response updatePet(@PathParam("id") final Long id, final Pet pet) {
		// TODO: MISSING REQUIREMENT: Should any validation be done on name?
		// TODO: Improvement - return 400 if invalid pet but 404 if invalid id
		pet.setId(id);
		final Pet updated = petService.updatePet(pet);
		return Response.status(updated == null ? Response.Status.NOT_FOUND : Response.Status.OK).entity(updated).build();
	}

	@DELETE
	@Path("{id}")
	@Produces("application/json")
	public Response deletePet(@PathParam("id") final Long id) {
		final boolean deleted = petService.deletePet(id);
		return Response.status(deleted ? Response.Status.NO_CONTENT : Response.Status.NOT_FOUND).build();
	}

}
