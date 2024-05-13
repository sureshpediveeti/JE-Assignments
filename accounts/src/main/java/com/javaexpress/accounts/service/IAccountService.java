package com.javaexpress.accounts.service;

import com.javaexpress.accounts.dto.CustomerDto;

public interface IAccountService {
	
	public void createAccount(CustomerDto customerDto);
	
	public CustomerDto fetchAccount(String mobileNumber);
	
	public boolean updateAccount(CustomerDto customerDto);
	
	public boolean deleteAccount(String mobileNumber);

}
