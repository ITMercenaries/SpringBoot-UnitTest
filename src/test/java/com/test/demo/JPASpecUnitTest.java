package com.test.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.demo.entity.Customer;
import com.test.demo.repo.CustomerRepository;
import com.test.demo.repo.CustomerSpecifications;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JPASpecUnitTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	CustomerRepository repository;

	@Test
	public void should_find_Smith_Only() {
		Customer customer1 = new Customer("Jack", "Smith");
		Customer customer2 = new Customer("Adam", "Johnson");
		Customer customer3 = new Customer("Peter", "Smith");
		entityManager.persist(customer1);
		entityManager.persist(customer2);
		entityManager.persist(customer3);

		Iterable<Customer> customers = repository.findAll( CustomerSpecifications.customerByLastname("Smith"));  
		assertThat(customers).hasSize(2).contains(customer1, customer3);
	}

	@Test
	public void should_filter_Smith_Out() {
		Customer customer1 = new Customer("Jack", "Smith");
		Customer customer2 = new Customer("Adam", "Johnson");
		Customer customer3 = new Customer("Peter", "Smith");
		entityManager.persist(customer1);
		entityManager.persist(customer2);
		entityManager.persist(customer3);
		
		Iterable<Customer> customers = repository.findAll( CustomerSpecifications.customerByLastname("Johnson"));  
		assertThat(customers).hasSize(1).contains(customer2);
	}
	
	@Test
	public void should_find_Jiameng() {
		Customer customer1 = new Customer("Jiameng", "Yu");
		Customer customer2 = new Customer("Jim", "Yu");
		entityManager.persist(customer1);
		entityManager.persist(customer2);

		Iterable<Customer> customers = repository.findAll( CustomerSpecifications.customerByFirstname("Jiameng"));  
		assertThat(customers.iterator().next()).isEqualTo(customer1);
	}	
}
