package com.javaexpress.loans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.loans.dto.LoansContactDetailsDto;
import com.javaexpress.loans.dto.LoansDto;
import com.javaexpress.loans.service.ILoansService;

import lombok.extern.slf4j.Slf4j;

@RefreshScope
@Slf4j
@RestController
@RequestMapping(path = "api")
public class LoansController {

    @Autowired
    private ILoansService iLoansService;
    
    @Value("${build.version}")
    private String buildVersion;
    
    @Autowired
    private Environment sytemEnvVariables;
    
    @Autowired
    private LoansContactDetailsDto loansContactDetail;

    @PostMapping("/create")
    public String createLoans(@RequestBody LoansDto loansDto) {
        log.info("LoansController :: createLoans");
        iLoansService.createLoans(loansDto);
        return "Loans created successfully";
    }

	@GetMapping("/fetch")
	public LoansDto fetchLoansDetails(@RequestParam String mobileNumber){
        log.info("LoansController :: fetchLoansDetails");
        return iLoansService.fetchLoans(mobileNumber);
	}

    @PutMapping("/update")
    public boolean updateLoanDetails(@RequestBody LoansDto inputLoansDto){
        log.info("LoansController :: updateLoanDetails");
        return iLoansService.updateLoans(inputLoansDto);
    }

    @DeleteMapping("/delete")
    public boolean deleteLoan(@RequestParam String mobileNumber){
        log.info("LoansController :: deleteLoan");
        return iLoansService.deleteLoans(mobileNumber);
    }
    
    @GetMapping("/build-info")
    public String getBuildInfo() {
    	return buildVersion;
    }
    
    @GetMapping("/env-maven-version")
    public String getMavenVersion() {
    	String mavenversion =  sytemEnvVariables.getProperty("M2_HOME");
		return mavenversion;
    }
    
	
	@GetMapping("/contact-info")
	public LoansContactDetailsDto getContactDetails() {
		return loansContactDetail;
	}

}
