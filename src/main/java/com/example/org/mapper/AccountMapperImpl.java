package com.example.org.mapper;

import org.springframework.stereotype.Component;

import com.example.org.dto.AccountRequestDTO;
import com.example.org.dto.AccountResponseDTO;
import com.example.org.model.Account;

@Component
public class AccountMapperImpl implements AccountMapper {

	@Override
	public Account toEntity(AccountRequestDTO accountRequestDTO) {
		if(accountRequestDTO==null) {
			return null;
		}
		
		Account account=new Account();
		account.setCustomerName(accountRequestDTO.getCustomerName());
		account.setCustomerMobileNumber(accountRequestDTO.getCustomerMobileNumber());
		account.setCustomerEmail(accountRequestDTO.getCustomerEmail());
		account.setCustomerAddress(accountRequestDTO.getCustomerAddress());
		account.setAccountType(accountRequestDTO.getAccountType());
		account.setBalance(accountRequestDTO.getBalance());
		
		return account;
	}

	@Override
	public AccountResponseDTO toResponseDTO(Account account) {
		if(account==null) {
			return null;
		}
		
		AccountResponseDTO accountResponseDTO=new AccountResponseDTO();
		accountResponseDTO.setAccountId(account.getAccountId());
		accountResponseDTO.setCustomerName(account.getCustomerName());
		accountResponseDTO.setCustomerMobileNumber(account.getCustomerMobileNumber());
		accountResponseDTO.setCustomerEmail(account.getCustomerEmail());
		accountResponseDTO.setCustomerAddress(account.getCustomerAddress());
		accountResponseDTO.setAccountType(account.getAccountType());
		accountResponseDTO.setBalance(account.getBalance());
		
		return accountResponseDTO;
	}

}
