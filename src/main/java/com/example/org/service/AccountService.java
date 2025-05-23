package com.example.org.service;

import java.util.List;

import java.util.UUID;

import com.example.org.dto.AccountRequestDTO;
import com.example.org.dto.AccountResponseDTO;

public interface AccountService {
	
	 AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO);
	
	 List<AccountResponseDTO> getAllAccounts();
	
	 AccountResponseDTO getAccountById(UUID accountId);
	
	 AccountResponseDTO updateAccount(UUID accountId, AccountRequestDTO accountRequestDTO);
	
	 void deleteAccount(UUID accountId);
}
