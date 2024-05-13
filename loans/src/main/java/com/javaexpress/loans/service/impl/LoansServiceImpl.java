package com.javaexpress.loans.service.impl;

import com.javaexpress.loans.exceptions.LoanAlreadyExistInDBException;
import com.javaexpress.loans.exceptions.LoanDoesNotExistException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.javaexpress.loans.dto.LoansDto;
import com.javaexpress.loans.entity.Loans;
import com.javaexpress.loans.repository.LoansRepository;
import com.javaexpress.loans.service.ILoansService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class LoansServiceImpl implements ILoansService {

    @Autowired
    private LoansRepository loansRepository;

    @Override
    public void createLoans(LoansDto loansDto) {
        Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(loansDto.getMobileNumber());

        if (optionalLoans.isPresent()) {
            throw new LoanAlreadyExistInDBException("Loans already exist in database for given mobile number: " + loansDto.getMobileNumber());
        }
        Loans loans = new Loans();

        BeanUtils.copyProperties(loansDto, loans);

        Long generateLoanNumber = 10000000L + new Random().nextLong(40000000L);

        loans.setLoanNumber(generateLoanNumber);

        loansRepository.save(loans);
    }

    @Override
    public LoansDto fetchLoans(String mobileNumber) {
        Loans dbLoan = loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new LoanDoesNotExistException("Loan not exist in database for given mobile number: "+mobileNumber));

        LoansDto loansDto = new LoansDto();
        BeanUtils.copyProperties(dbLoan, loansDto);

        return loansDto;
    }

    @Override
    public boolean updateLoans(LoansDto updateLoansDto) {
        Loans dbLoans = loansRepository.findById(updateLoansDto.getLoanId()).
                orElseThrow(() -> new LoanDoesNotExistException("Loan not exist in database for given loan id: "+updateLoansDto.getLoanId()));

        BeanUtils.copyProperties(updateLoansDto, dbLoans);

        loansRepository.save(dbLoans);

        return true;
    }

    @Override
    public boolean deleteLoans(String mobileNumber) {
        Loans dbLoan = loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new LoanDoesNotExistException("Loan not exist in database for given mobile number: "+mobileNumber));

        // for mobile linked with multiple loans
        loansRepository.deleteById(dbLoan.getLoanId());

        return true;
    }

}
