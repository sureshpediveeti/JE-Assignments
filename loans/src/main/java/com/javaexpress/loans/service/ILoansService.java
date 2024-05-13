package com.javaexpress.loans.service;

import com.javaexpress.loans.dto.LoansDto;

public interface ILoansService {
	
	public void createLoans(LoansDto loansDto);
	
	public LoansDto fetchLoans(String mobileNumber);
	
	public boolean updateLoans(LoansDto loansDto);
	
	public boolean deleteLoans(String mobileNumber);

}
