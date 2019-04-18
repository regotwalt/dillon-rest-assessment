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
		if (id == null) return null;
		try {
			return personMapper.getPersonById(id);
		}
		catch (PersistenceException pe) {
			// Improvement: Log error
			return null;
		}
	}

	public Person createPerson(final Person person) {
		if (person == null) return null;
		try {
			final int rowsCreated = personMapper.createPerson(person);
			// Future - log if rowsCreated is not 0 or 1 (unexpected)
			if (rowsCreated == 0) return null;
			return person;
		}
		catch (PersistenceException pe) {
			// Improvement: Log error
			return null;
		}
	}

	public Person updatePerson(final Person person) {
		if (person == null) return null;
		try {
			final int rowsUpdated = personMapper.updatePerson(person);
			// Future - log if rowsUpdated is not 0 or 1 (unexpected)
			if (rowsUpdated == 0) return null;
			return person;
		}
		catch (PersistenceException pe) {
			// Improvement: Log error
			return null;
		}
	}

	public boolean deletePerson(final Long personId) {
		if (personId == null) return false;
		try {
			final int numberDeleted = personMapper.deletePerson(personId);
			// Future - log if numberDeleted is not 0 or 1 (unexpected)
			return numberDeleted > 0;
		}
		catch (PersistenceException pe) {
			// Improvement: Log error
			return false;
		}
	}

}
