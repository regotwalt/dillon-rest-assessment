package com.hoopladigital.service;

import com.hoopladigital.bean.Person;
import com.hoopladigital.mapper.PersonMapper;

import javax.inject.Inject;
import java.util.List;

public class PersonService {

	private final PersonMapper personMapper;

	@Inject
	public PersonService(final PersonMapper personMapper) {
		this.personMapper = personMapper;
	}

	public List<Person> getPersonList() {
		return personMapper.getPersonList();
	}

	public Person getPersonById(Long id) {
		return personMapper.getPersonById(id);
	}

	public Person createPerson(Person person) {
		if (person == null) return null;
		personMapper.createPerson(person);
		return person;
	}

}
