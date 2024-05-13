package com.javaexpress.accounts.mapper;

import com.javaexpress.accounts.dto.CustomerDto;
import com.javaexpress.accounts.entity.Customer;

public class CustomerMapper {

	public static CustomerDto mapToCustomerDto(CustomerDto customerDto, Customer customer) {
		
		customerDto.setName(customer.getName());
		customerDto.setMobileNumber(customer.getMobileNumber());
		customerDto.setEmail(customer.getEmail());
		
		return customerDto;
	}

	public static Customer mapToCustomer(Customer customer, CustomerDto customerDto) {

		customer.setName(customerDto.getName());
		customer.setMobileNumber(customerDto.getMobileNumber());
		customer.setEmail(customerDto.getEmail());
		
		return customer;
	}

}
