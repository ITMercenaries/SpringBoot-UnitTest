package com.test.demo.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.test.demo.entity.Customer;
import com.test.demo.repo.CustomerRepository;
import com.test.demo.repo.CustomerSpecifications;

@Component("CustomerService")
public class CustomerServiceImpl  implements CustomerService {

	private final CustomerRepository repository;

	public CustomerServiceImpl(CustomerRepository repository) {
		this.repository = repository;
	}

	@Override
	public int findVIPLength(Specification<Customer> vipSpec) {  //Should use a parameter, hardcoded for clarity and simplicity
	//	Specification<Customer> vipSpec = CustomerSpecifications.customerByFirstname("Peter");
		
		Iterable<Customer> customers = repository.findAll(vipSpec);  
		int valuablelength = 0;
		
		if (customers.iterator().hasNext()){
			valuablelength = customers.iterator().next().getLastName().length();
		}
		
		return valuablelength;
	}
}
