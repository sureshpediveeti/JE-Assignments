package com.javaexpress.loans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.loans.entity.Loans;

import java.util.Optional;

public interface LoansRepository extends JpaRepository<Loans, Long> {

    Optional<Loans> findByMobileNumber(String mobileNumber);
}
