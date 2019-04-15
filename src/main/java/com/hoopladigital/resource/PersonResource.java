package com.hoopladigital.resource;

import com.hoopladigital.bean.Person;
import com.hoopladigital.bean.Pet;
import com.hoopladigital.service.PersonService;
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

@Path("/people")
public class PersonResource {

	private final PersonService personService;
	private final PetService petService;

	@Inject
	public PersonResource(final PersonService personService, final PetService petService) {
		this.personService = personService;
		this.petService = petService;
	}

	@GET
	@Produces("application/json")
	public List<Person> getPersonList() {
		return personService.getPersonList();
	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response getPersonById(@PathParam("id") Long id) {
		final Person person = personService.getPersonById(id);
		return Response.status(person == null ? Response.Status.NOT_FOUND : Response.Status.OK).entity(person).build();
	}

	@GET
	@Path("{id}/pets")
	@Produces("application/json")
	public Response getPetListByPersonId(@PathParam("id") Long personId) {
		final List<Pet> pets = petService.getPetListByPersonId(personId);
		final boolean validRequest = !pets.isEmpty() || personService.getPersonById(personId) != null;
		return Response.status(validRequest ? Response.Status.OK : Response.Status.NOT_FOUND).entity(validRequest ? pets : null).build();
	}

	@POST
	@Produces("application/json")
	public Person createPerson(Person person) {
		// TODO: MISSING REQUIREMENTS: Should any validation be done on Person (specifically first/middle/last name)?
		return personService.createPerson(person);
	}

	@PUT
	@Path("{id}")
	@Produces("application/json")
	public Response updatePerson(@PathParam("id") Long id, Person person) {
		// TODO: MISSING REQUIREMENTS: Should any validation be done on Person (specifically first/middle/last name - id covered)?
		person.setId(id);
		final Person updated = personService.updatePerson(person);
		return Response.status(updated == null ? Response.Status.NOT_FOUND : Response.Status.OK).entity(updated).build();
	}

	@DELETE
	@Path("{id}")
	@Produces("application/json")
	public Response deletePerson(@PathParam("id") Long id) {
		final boolean deleted = personService.deletePerson(id);
		return Response.status(deleted ? Response.Status.NO_CONTENT : Response.Status.NOT_FOUND).build();
	}

}
