package com.redhat.training.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;
import javax.persistence.PersistenceException;
//import persistence related libraries
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;

import com.redhat.training.model.Person;

@Stateless

public class PersonService {
	// Container level entity-manager
	// 	propagates entities context where an EntityManager instance is present
	@PersistenceContext(unitName = "hello")
	EntityManager em;
	
	// Simple non-RESTy method for JSF bean invocation
	public String hello(String name) {
		try {
			// let's grab the current date and time on the server
			LocalDateTime today = LocalDateTime.now();

			// format it nicely for on-screen display
			DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm:ss a");
			String fdate = today.format(format);

			// Create a new Person object and persist to database
			Person p = new Person();
			p.setName(name);
			em.persist(p);
			
			// respond back with Hello and convert the name to UPPERCASE. Also, send the
			// 	current time on the server.
			return "Hello " + name.toUpperCase() + "!. " + "Time on the server is: " + fdate;
			
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}

	// Fetch result by Person id 
	public String getPerson(Long id) {
		Person p = null;
		String personName = null;
		
		try {
			p = em.find(Person.class, id);
			if ( p != null) {
				System.out.println("Personservice > Found Person for id "+ id);
				personName = p.getPersonName();
				
			} else {
				System.out.println("Personservice Warning > Found NULL Person for id '"+ id+"'. Removing..");
				personName = null;
			}
		} catch (PersistenceException pe) {
			System.out.println("Personservice ERROR > NOT Found any Person for id "+ id );
			personName = null;
		}
		return personName;
	}
	

	// Get all Person objects in the Database
	public List<Person> getAllPersons() {
		TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
		List<Person> persons = query.getResultList();

		return persons;
	}

}
