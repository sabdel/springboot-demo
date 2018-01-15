package com.example.demo.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Person;
import com.example.demo.repo.PersonRepository;
import com.example.demo.service.ServicePerson;



@RestController
@CrossOrigin
public class RestPersonController {
	@Autowired ServicePerson servicePerson;
	@Autowired PersonRepository personRepository;


	@RequestMapping(value ="/persons" ,method = RequestMethod.GET )
	public Page<Person> getAllPerson(@RequestParam(name ="page",defaultValue="0") int page,@RequestParam(name ="size",defaultValue="3") int size){
		return personRepository.findAll(new PageRequest(page, size));
	}
	
	@RequestMapping(value ="/findbyAge" ,method = RequestMethod.GET )
	public Page<Person> getAllPersonByAge(@RequestParam("age")int age,
			@RequestParam(name ="page",defaultValue="0")int page,
			@RequestParam(name ="size",defaultValue="3")int size){
		return personRepository.findByAgeOlder(age,new PageRequest(page, size));
	}

	
	@RequestMapping(value ="/persons/{id}" ,method = RequestMethod.GET )
	public Person getPersonById(@PathVariable("id")Long id){
		 Optional<Person> person = servicePerson.getPerson(id);
		return person.isPresent() ? person.get() : null;
	}

	@RequestMapping(value ="/persons" ,method = RequestMethod.POST )
	public Person add(@RequestBody Person person){
		 Person p = servicePerson.updatePerson(person);
		 return p;
//		 if(p != null){
//			 URI location = ServletUriComponentsBuilder
//						.fromCurrentRequest().path("/{id}")
//						.buildAndExpand(p.getId()).toUri();
//
//			 return ResponseEntity.created(location).build();
//
//		 }else{
//			 return ResponseEntity.noContent().build();
//		 }
	}

	@RequestMapping(value ="/persons/{id}" ,method = RequestMethod.PUT )
	public ResponseEntity<?> update(@RequestBody Person person,@PathVariable("id")Long id){
		 person.setId(id);
		 Person p = servicePerson.updatePerson(person);
		 if(p != null){
			 URI location = ServletUriComponentsBuilder
						.fromCurrentRequest().path("/{id}")
						.buildAndExpand(p.getId()).toUri();

			 return ResponseEntity.created(location).build();

		 }else{
			 return ResponseEntity.noContent().build();
		 }
	}

	@RequestMapping(value ="/persons/{id}" ,method = RequestMethod.DELETE )
	public void delete(@PathVariable("id")Long id){
        Optional<Person> p = servicePerson.getPerson(id);
        servicePerson.deletePerson(p.get());
		
	}
//	@RequestMapping(method = RequestMethod.POST)
//	ResponseEntity<?> add(@PathVariable String userId, @RequestBody Bookmark input) {
//		this.validateUser(userId);
//
//		return servicePerson.getAllPerson()
//				.forEach(account -> {
//					Bookmark result = bookmarkRepository.save(new Bookmark(account,
//							input.uri, input.description));
//					URI location = ServletUriComponentsBuilder
//						.fromCurrentRequest().path("/{id}")
//						.buildAndExpand(result.getId()).toUri();
//
//					return ResponseEntity.created(location).build();
//				})
//				.orElse(ResponseEntity.noContent().build());
//
//	}
	 
	
}
