package com.hoopladigital.resource;

import com.hoopladigital.bean.Person;
import com.hoopladigital.service.PersonService;

import javax.inject.Inject;
import javax.ws.rs.GET;
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

}
