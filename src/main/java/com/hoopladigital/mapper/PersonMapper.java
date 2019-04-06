package com.hoopladigital.mapper;

import com.hoopladigital.bean.Person;

import java.util.List;

public interface PersonMapper {
	List<Person> getPersonList();
	Person getPersonById(Long id);
	void createPerson(Person person);
	void updatePerson(Person person);
	int deletePerson(Person person);
}
