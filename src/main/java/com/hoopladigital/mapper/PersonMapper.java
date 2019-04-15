package com.hoopladigital.mapper;

import com.hoopladigital.bean.Person;

import java.util.List;

public interface PersonMapper {
	List<Person> getPersonList();
	Person getPersonById(Long id);
	int createPerson(Person person);
	int updatePerson(Person person);
	int deletePerson(Long id);
}
