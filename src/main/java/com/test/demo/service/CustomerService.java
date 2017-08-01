package com.test.demo.service;

import org.springframework.data.jpa.domain.Specification;

import com.test.demo.entity.Customer;

public interface CustomerService {
	
	int findVIPLength(Specification<Customer> vipSpec);

}
