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
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.demo.entity.Customer;
import com.test.demo.service.CustomerService;
import com.test.demo.service.CustomerServiceImpl;
import com.test.demo.repo.CustomerRepository;
import com.test.demo.repo.CustomerSpecifications;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JPAServiceUnitTest {

	private CustomerService customerService;
    private CustomerRepository mockRepository;	

	Customer customer1 = new Customer("Jack", "Smith");
	Customer customer2 = new Customer("Adam", "Johnson");
	Customer customer3 = new Customer("Peter", "Smith");
    
	@Before
    public void setUp() {
		mockRepository = Mockito.mock(CustomerRepository.class);
		customerService = new CustomerServiceImpl(mockRepository);
    }
    
	@Test
	public void should_find_VIP_length() {

		//Test Service Layer functions -- Sample only 
		Specification<Customer> mySpec = CustomerSpecifications.customerByFirstname("Peter");
		List<Customer> rsltPeter = new ArrayList<Customer>();
		rsltPeter.add(customer3);
		when(mockRepository.findAll(mySpec)).thenReturn(rsltPeter);

		int length = customerService.findVIPLength(mySpec);
		
		// Verify the result is as your expected!
		assertThat(length).isEqualTo(5);
		verify(mockRepository, times(1)).findAll(mySpec);
	}
	
}
