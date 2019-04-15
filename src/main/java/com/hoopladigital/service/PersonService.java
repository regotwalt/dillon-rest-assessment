package com.hoopladigital.service;

import com.hoopladigital.bean.Person;
import com.hoopladigital.mapper.PersonMapper;

import javax.inject.Inject;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

public class PersonService {

	private final PersonMapper personMapper;

	@Inject
	public PersonService(final PersonMapper personMapper) {
		this.personMapper = personMapper;
	}

	public List<Person> getPersonList() {
		return personMapper.getPersonList();
	}

	public Person getPersonById(final Long id) {
		return personMapper.getPersonById(id);
	}

	public Person createPerson(final Person person) {
		if (person == null) return null;
		personMapper.createPerson(person);
		return person;
	}

	public Person updatePerson(final Person person) {
		if (person == null) return null;
		try {
			final int rowsUpdated = personMapper.updatePerson(person);
			if (rowsUpdated == 0) return null;
		}
		catch (PersistenceException pe) {
			return null;
		}
		return person;
	}

	public boolean deletePerson(final Long personId) {
		if (personId == null) return false;
		try {
			final int numberDeleted = personMapper.deletePerson(personId);
			// Future: Log WARN if numberDeleted>1 - would be unexpected
			return numberDeleted > 0;
		}
		catch (PersistenceException pe) {
			return false;
		}
	}

}
