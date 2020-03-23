package com.redhat.training.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;

import com.redhat.training.model.Person;

@Stateless

public class PersonService {

	@PersistenceContext(unitName = "hello")
	private EntityManager entityManager;

	public List<Person> getAllPersons() {

		TypedQuery<Person> all_persons = entityManager.createQuery("SELECT p FROM Person p ", Person.class);
		return all_persons.getResultList();
	}

	
	// Get persons whose name matches the name given in the query
	public List<Person> getPersonsWithName(String name) {
		System.out.println("PersonService>getPersonsWithName> Searching for Person "+ name);
		// TypedQuery<Person> all_persons = entityManager.createQuery("SELECT p FROM Person p where p.name = ?1", Person.class);
		TypedQuery<Person> all_persons = entityManager.createNamedQuery("getAllPersonWithName", Person.class)
			.setParameter("pname", name);
		
		return all_persons.getResultList();
	}
}
