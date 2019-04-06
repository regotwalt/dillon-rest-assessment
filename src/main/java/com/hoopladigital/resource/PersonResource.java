package com.hoopladigital.resource;

import com.hoopladigital.bean.Person;
import com.hoopladigital.service.PersonService;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/people")
public class PersonResource {

	private final PersonService personService;

	@Inject
	public PersonResource(final PersonService personService) {
		this.personService = personService;
	}

	@GET
	@Produces("application/json")
	public List<Person> getPersonList() {
		return personService.getPersonList();
	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public Person getPersonById(@PathParam("id") Long id) {
		// TODO: Deal with response code (404 if invalid id)
		// TODO: Test endpoint
		return personService.getPersonById(id);
	}

	@POST
	@Produces("application/json")
	public Person createPerson(@PathParam("firstName") String firstName,
							   @PathParam("middleName") String middleName,
							   @PathParam("lastName") String lastName) {
		// TODO: Test endpoint
		// TODO: MISSING REQUIREMENTS: Should any validation be done on first/middle/last names?

		final Person person = new Person();
		person.setFirstName(firstName);
		person.setMiddleName(middleName);
		person.setLastName(lastName);

		return personService.createPerson(person);
	}

	@PUT
	@Produces("application/json")
	public Person updatePerson(@PathParam("id") Long id,
							   @PathParam("firstName") String firstName,
							   @PathParam("middleName") String middleName,
							   @PathParam("lastName") String lastName) {

		// TODO: Test endpoint
		// TODO: MISSING REQUIREMENTS: Should any validation be done on first/middle/last names?
		// TODO: Another option - make all fields optional, update only what's provided

		final Person person = new Person();
		person.setId(id);
		person.setFirstName(firstName);
		person.setMiddleName(middleName);
		person.setLastName(lastName);

		return personService.updatePerson(person);
	}

	@DELETE
	@Produces("application/json")
	public boolean deletePerson(@PathParam("id") Long id) {

		// TODO: Test endpoint

		final Person person = new Person();
		person.setId(id);

		return personService.deletePerson(person);
	}

}
