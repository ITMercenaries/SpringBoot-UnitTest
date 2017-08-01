package com.test.demo.repo;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.test.demo.entity.Customer;

public class CustomerSpecifications {
	  private CustomerSpecifications() {}    
	  
	  public static Specification<Customer> customerByFirstname(String fname) {
	    return new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		        return cb.equal(root.get("firstName"), fname);
			}
	    };
	  }
	  
	  public static Specification<Customer> customerByLastname(String lname) {
		  
	    return new Specification<Customer>(){
	    	@Override
		    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		      return cb.equal(root.get("lastName"), lname);
		    }
	    };
	 }

}


