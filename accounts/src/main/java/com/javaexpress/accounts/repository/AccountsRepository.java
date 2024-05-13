package com.javaexpress.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.javaexpress.accounts.entity.Accounts;

import jakarta.transaction.Transactional;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
	
	Optional<Accounts> findByCustomerId(Long customerId);
	
	
	/* 
	 * The @Transactional and @Modifying annotations are both used in Spring Boot JPA to manage transactions. The @Transactional annotation is used to declare a method as transactional, which means that it will be executed within a transaction.
	 * 
	 * Good practice to use the @Transactional annotation for all methods that modify data, is not required for methods that only read data
	 * The @Transactional annotation can be used at the method level or the class level.
	 *  
	 * The @Modifying annotation is used to indicate that a method modifies data, which means that it will need to be executed within a transaction to ensure data integrity.
	 * 
	 * The @Modifying annotation can only be used at the method level.
	 */
	@Transactional
	@Modifying
	void deleteByCustomerId(Long customerId);
	
}
