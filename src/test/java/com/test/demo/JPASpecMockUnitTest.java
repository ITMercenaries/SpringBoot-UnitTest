package com.test.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.demo.entity.Customer;
import com.test.demo.service.CustomerService;
import com.test.demo.service.CustomerServiceImpl;
import com.test.demo.repo.CustomerRepository;
import com.test.demo.repo.CustomerSpecifications;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JPASpecMockUnitTest {

	@MockBean
	private CustomerRepository mockRepository;
	
	Customer customer1 = new Customer("Jack", "Smith");
	Customer customer2 = new Customer("Adam", "Johnson");
	Customer customer3 = new Customer("Peter", "Smith");
    
	@Test
	public void should_find_Smith_Only() {
		Specification<Customer> mySpec = CustomerSpecifications.customerByLastname("Smith");

		List<Customer> rsltSmiths = new ArrayList<Customer>();
		rsltSmiths.add(customer1);
		rsltSmiths.add(customer3);
		
		when(mockRepository.findAll(mySpec)).thenReturn(rsltSmiths);

		//testing your code here by using Mocked beans... 
		Iterable<Customer> customers = mockRepository.findAll(mySpec);  
		
		// Verify the result is as your expected!
		assertThat(customers).hasSize(2).contains(customer1, customer3);
		verify(mockRepository, times(1)).findAll(mySpec);
		assertThat(customers.iterator().next().getFirstName()).isEqualTo("Jack");
	}

	@Test
	public void should_find_Peter_Only() {
		Specification<Customer> mySpec = CustomerSpecifications.customerByFirstname("Peter");
		List<Customer> rsltPeter = new ArrayList<Customer>();
		rsltPeter.add(customer3);
		
		when(mockRepository.findAll(mySpec)).thenReturn(rsltPeter);

		//testing your code here by using Mocked beans... 
		// But there is little to no value added here... ...
		Iterable<Customer> customers = mockRepository.findAll(mySpec);  
		
		// Verify the result is as your expected!
		assertThat(customers).hasSize(1).contains(customer3);
		verify(mockRepository, times(1)).findAll(mySpec);
		assertThat(customers.iterator().next().getLastName()).isEqualTo("Smith");
	}
}
