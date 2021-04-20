package com.showmax.cruddemo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.showmax.cruddemo.exceptions.ApiException;
import com.showmax.cruddemo.exceptions.ExpectationFailedException;
import com.showmax.cruddemo.exceptions.ResourceNotFoundException;
import com.showmax.cruddemo.model.Customer;
import com.showmax.cruddemo.repo.CustomerRepo;



@Service
public class CustomerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
	@Autowired
	private CustomerRepo customerRepo;
	
	public Customer create(Customer customer) throws ApiException {
		try {
			// Save customer on MongoDB
			Customer savedCustomer = customerRepo.insert(customer);
			
			return savedCustomer;
		}catch(Exception e) {
			// Log error
			LOGGER.error("Exception occured Cause {} Message {} exception {}", e.getCause(), e.getMessage(), e);
			throw new ExpectationFailedException("Expectation Failed On Actions Performed On Resource");
		}
		
	}
	
	public Customer getById(String id) throws ApiException{
		try {
			// Find or throw exception (404 status code)
			Customer savedCustomer = customerRepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException(String.format("Resource with ID :%s Not Found!", id)));
			
			return savedCustomer;
		}catch(Exception e) {
			// Log error
			LOGGER.error("Exception occured Cause {} Message {} exception {}", e.getCause(), e.getMessage(), e);
			throw new ExpectationFailedException("Expectation Failed On Actions Performed On Resource");
		}
	}
	
	public List<Customer> getAll() throws ApiException{
		try {
			List<Customer> allCustomers = customerRepo.findAll();
			
			// Throw exception (404 status code)
			if(allCustomers.isEmpty()) {
				throw new ResourceNotFoundException("Resources Not Found!");
			}
			
			return allCustomers;
		}catch(Exception e) {
			// Log error
			LOGGER.error("Exception occured Cause {} Message {} exception {}", e.getCause(), e.getMessage(), e);
			throw new ExpectationFailedException("Expectation Failed On Actions Performed On Resource");
		}
	}
	
	public Customer put(Customer customer) throws ApiException{
		try {
			// Check if customer exist before update
			Customer savedCustomer = this.getById(customer.getId());
			
			// Update customer on MongoDB
			return customerRepo.save(customer);
		}catch(Exception e) {
			// Log error
			LOGGER.error("Exception occured Cause {} Message {} exception {}", e.getCause(), e.getMessage(), e);
			throw new ExpectationFailedException("Expectation Failed On Actions Performed On Resource");
		}
	}
	
	public void deleteCustomerById(String id) throws ApiException{
		try {
			customerRepo.deleteById(id);
		}catch(Exception e) {
			// Log error
			LOGGER.error("Exception occured Cause {} Message {} exception {}", e.getCause(), e.getMessage(), e);
			throw new ExpectationFailedException("Expectation Failed On Actions Performed On Resource");
		}
	}
	
	
}
