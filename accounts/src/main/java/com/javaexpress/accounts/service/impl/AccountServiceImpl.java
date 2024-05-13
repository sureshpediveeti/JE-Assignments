package com.javaexpress.accounts.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.javaexpress.accounts.dto.AccountsDto;
import com.javaexpress.accounts.dto.CustomerDto;
import com.javaexpress.accounts.entity.Accounts;
import com.javaexpress.accounts.entity.Customer;
import com.javaexpress.accounts.exceptions.CustomerAlreadyExistsException;
import com.javaexpress.accounts.exceptions.ResourceNotFoundException;
import com.javaexpress.accounts.mapper.AccountsMapper;
import com.javaexpress.accounts.mapper.CustomerMapper;
import com.javaexpress.accounts.repository.AccountsRepository;
import com.javaexpress.accounts.repository.CustomerRepository;
import com.javaexpress.accounts.service.IAccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor // if use this annotation @Autowired not required
public class AccountServiceImpl implements IAccountService {

	private AccountsRepository accountsRepository;
	private CustomerRepository customerRepository;

	@Override
	public void createAccount(CustomerDto customerDto) {
		// convert customerDto to customer
		Customer customer = CustomerMapper.mapToCustomer(new Customer(), customerDto);

		// validate mobile number in db
		Optional<Customer> byMobileNumber = customerRepository.findByMobileNumber(customer.getMobileNumber());

		if (byMobileNumber.isPresent()) {
			throw new CustomerAlreadyExistsException(
					"Customer already register with the Mobile Number : " + customer.getMobileNumber());
		}

		// save customer information database
		var dbCustomer = customerRepository.save(customer);

		// create new account and insert in db
		accountsRepository.save(createNewAccount((dbCustomer)));

	}

	private Accounts createNewAccount(Customer dbCustomer) {
		Accounts newAccounts = new Accounts();
		newAccounts.setCustomerId(dbCustomer.getCustomerId());
		newAccounts.setAccountType("SAVINGS");
		newAccounts.setBranchAddress("12th Street, puppalagud, Manikind, Hydarabad");

		long randomAccountNumber = 10000000L + new Random().nextInt(900000000);

		newAccounts.setAccountNumber(randomAccountNumber);

		return newAccounts;
	}

	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer Mobile Number Not Found : " + mobileNumber));

		Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Account not found for Mobile Number : " + mobileNumber));

		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(new CustomerDto(), customer);

		customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(new AccountsDto(), account));

		return customerDto;
	}

	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		boolean isUpdated = false;

		AccountsDto accountsDto = customerDto.getAccountsDto();

		if (accountsDto != null) {
			Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
					.orElseThrow(() -> new ResourceNotFoundException(
							"Accoount not found for id : " + accountsDto.getAccountNumber()));

			AccountsMapper.mapToAccounts(accounts, customerDto.getAccountsDto());
			accountsRepository.save(accounts);

			Customer customer = customerRepository.findById(accounts.getCustomerId()).orElseThrow(
					() -> new ResourceNotFoundException("Customer Not Found for id : " + accounts.getCustomerId()));
			CustomerMapper.mapToCustomer(customer, customerDto);
			customerRepository.save(customer);
			isUpdated = true;

		} else {
			throw new RuntimeException("Update operation faild. Please try again with valid input");
		}

		return isUpdated;
	}

	@Override
	public boolean deleteAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
				() -> new ResourceNotFoundException("Customer Not Found for Mobile Number : " + mobileNumber));
		
		accountsRepository.deleteByCustomerId(customer.getCustomerId());
		
		customerRepository.deleteById(customer.getCustomerId());
		
		return true;
	}

}
