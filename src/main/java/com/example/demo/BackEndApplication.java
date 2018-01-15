package com.example.demo;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.service.ServicePerson;

@SpringBootApplication
public class BackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndApplication.class, args);
	}
	
	@Bean
	 CommandLineRunner init(ServicePerson servicePerson) {
	        return (args) ->{
	            Arrays.asList("jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
	                .forEach(a -> {
                          servicePerson.createPerson(a, 50);
	                });
	            
	            Arrays.asList("s1,s2,s3,s4,s5,s6,s7,s8".split(","))
                .forEach(a -> {
                      servicePerson.createPerson(a, 30);
                });
	             };
	    }
}
