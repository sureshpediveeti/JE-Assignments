package com.javaexpress.accounts.mapper;

import com.javaexpress.accounts.dto.AccountsDto;
import com.javaexpress.accounts.entity.Accounts;

public class AccountsMapper {

	public static AccountsDto mapToAccountsDto(AccountsDto accountsDto, Accounts accounts) {

		accountsDto.setAccountNumber(accounts.getAccountNumber());
		accountsDto.setAccountType(accounts.getAccountType());
		accountsDto.setBranchAddress(accounts.getBranchAddress());

		return accountsDto;
	}

	public static Accounts mapToAccounts(Accounts accounts, AccountsDto accountsDto) {
		
		accounts.setAccountNumber(accountsDto.getAccountNumber());
		accounts.setAccountType(accountsDto.getAccountType());
		accounts.setBranchAddress(accountsDto.getBranchAddress());

		return accounts;
	}

}
