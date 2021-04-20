package com.showmax.cruddemo;

import java.util.Objects;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.showmax.cruddemo.exceptions.ApiException;
import com.showmax.cruddemo.exceptions.ResourceNotFoundException;
import com.showmax.cruddemo.model.Customer;
import com.showmax.cruddemo.service.CustomerService;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class CrudDemoApplicationTests {
	@Autowired
	private CustomerService customerService;
	
	@Test
	@Order(1)
	public void test_fetch_all_resource_fail() throws ApiException{
		   
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			   customerService.getAll();
		   });
	}

	@Test
	@Order(2)
	public void test_fetch_resource_by_id_fail() throws ApiException{
		   
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
		   customerService.getById(Integer.toString(1));
		});
		  
	}
	
	@Test
	@Order(3)
	public void test_create_resource_success() throws ApiException{
		Customer resource =  customerService.create(buildCustomerRequest());
       
		Assert.assertEquals(Objects.nonNull(resource),true);
    }
	
	private Customer buildCustomerRequest() {
		Customer customer = new Customer();
		customer.setName("demo");
		customer.setSurname("crud");
		
		return customer;
	}

}
