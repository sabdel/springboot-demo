package com.example.demo.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	
	    @Query("select p  from Person p where p.age > :x")
	    public Page<Person> findByAgeOlder(@Param("x") Integer age, Pageable page);

}
