package com.smithdrug.useraccounts.repository;

import org.springframework.transaction.annotation.Transactional;

import com.smithdrug.useraccounts.entity.UserInformation;

@Transactional
public interface userAccountRepository {
	
	public UserInformation getUserInfo(String accountNumber);
	
}
