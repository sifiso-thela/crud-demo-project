package com.showmax.cruddemo.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.showmax.cruddemo.exceptions.ApiException;
import com.showmax.cruddemo.model.Customer;
import com.showmax.cruddemo.service.CustomerService;



@RestController
@RequestMapping(value = "/customer")
public class CustomerResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerResource.class);
	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/", produces = "application/json", consumes = "application/json")
	private @ResponseBody ResponseEntity<Object> createCustomer(@RequestBody(required = true) Customer customer) throws ApiException {
		LOGGER.debug("@@@ - Creating a customer ");

		try {
			return new ResponseEntity<>(customerService.create(customer), HttpStatus.CREATED);
		} catch (ApiException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(e.getCode()));
		}
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	private @ResponseBody ResponseEntity<Object> getCustomerByID(@PathVariable(name = "id", required = true) String id) throws ApiException {
		LOGGER.debug("@@@ - Get customer by id");

		try {
			return new ResponseEntity<>(customerService.getById(id), HttpStatus.OK);
		} catch (ApiException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(e.getLocalizedMessage()));
		}
	}

	@GetMapping(value = "/", produces = "application/json")
	private @ResponseBody ResponseEntity<Object> GetAllCustomer() throws ApiException {
		LOGGER.debug("@@@ - Get all customer");

		try {
			return new ResponseEntity<>(customerService.getAll(), HttpStatus.OK);
		} catch (ApiException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(e.getLocalizedMessage()));
		}
	}
	
	@PutMapping(value = "/", produces = "application/json", consumes = "application/json")
	private @ResponseBody ResponseEntity<Object> updateCustomer(@RequestBody(required = true) Customer customer) throws ApiException {
		LOGGER.debug("@@@ - Update a customer ");

		try {
			return new ResponseEntity<>(customerService.put(customer), HttpStatus.CREATED);
		} catch (ApiException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(e.getCode()));
		}
	}
	
	@DeleteMapping(value = "/{id}")
	private @ResponseBody ResponseEntity<Object> deleteCustomer(@PathVariable(name = "id", required = true) String id) throws ApiException {
		try {
			customerService.deleteCustomerById(id);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} catch (ApiException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(e.getCode()));
		}
	}
}
