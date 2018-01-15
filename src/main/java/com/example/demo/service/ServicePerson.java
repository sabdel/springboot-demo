package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Person;
import com.example.demo.repo.PersonRepository;

@Service
public class ServicePerson {

	@Autowired PersonRepository personRepository;
	
	public Person createPerson(String name, Integer age){
		return personRepository.saveAndFlush(new Person(name, age));
	}
	
	public Person updatePerson(final Person person){
		return personRepository.saveAndFlush(person);
	}

	public void deletePerson(final Person person){
		personRepository.delete(person);
	}

	public Optional<Person> getPerson(final Long id){
		return Optional.of(personRepository.getOne(id));
	}

	public List<Person> getAllPerson(){
		return personRepository.findAll();
	}
	
}
