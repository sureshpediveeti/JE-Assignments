package com.javaexpress.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Accounts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_number")
	private Long accountNumber;
	
	@Column(name = "customer_id")
	private Long customerId;
	
	@Column(name = "account_type")
	private String accountType;
	
	@Column(name = "branch_address")
	private String branchAddress;

}
