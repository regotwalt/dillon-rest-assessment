package com.hoopladigital.mapper;

import com.hoopladigital.bean.Person;

import java.util.List;

public interface PersonMapper {
	List<Person> getPersonList();
	Person getPersonById(final Long id);
	int createPerson(final Person person);
	int updatePerson(final Person person);
	int deletePerson(final Long id);
}
