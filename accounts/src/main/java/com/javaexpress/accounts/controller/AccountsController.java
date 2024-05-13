package com.javaexpress.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.accounts.dto.AccountsContactDetailsDto;
import com.javaexpress.accounts.dto.CustomerDto;
import com.javaexpress.accounts.service.IAccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "api")
public class AccountsController {

	@Autowired
	IAccountService iAccountService;
	
	@Value("${build.version}")
	private String buildVersionFromGit;
	
	@Autowired
	private Environment systemEnviranamentVariable;
	
	@Autowired
	private AccountsContactDetailsDto accountsContactDetails;

	@PostMapping("/create")
	public String create(@RequestBody CustomerDto customerDto) {
		log.info("AccountsController :: create");

		iAccountService.createAccount(customerDto);

		return "Account created Successfully";
	}

	@GetMapping("/fetch")
	public CustomerDto fetchAccount(@RequestParam String mobileNumber) {
		log.info("AccountsController :: fetchAccount");

		return iAccountService.fetchAccount(mobileNumber);
	}
	
	@PutMapping("/update")
	public Boolean updateAccountDetails(@RequestBody CustomerDto customerDto) {
		log.info("AccountsController :: updateAccount");
		
		return iAccountService.updateAccount(customerDto);
	}
	
	@DeleteMapping("/delete")
	public Boolean deleteAccount(@RequestParam String mobileNumber) {
		log.info("AccountsController :: deleteAccount");
		
		return iAccountService.deleteAccount(mobileNumber);
	}
	
	// this will fetch single value form git using @Value annotation
	
	@GetMapping("/build-info")
	public String buildVersion() {
		return buildVersionFromGit;
	}
	
	// this will fetch System environment variables Environment interface
	
	@GetMapping("/env-java-version")
	public String getJavaVersion() {
		String javaversion =  systemEnviranamentVariable.getProperty("JAVA_HOME");
		return javaversion;
	}
	
	@GetMapping("/contact-info")
	public AccountsContactDetailsDto getAccountContactDetails() {
		return accountsContactDetails;
	}
}
