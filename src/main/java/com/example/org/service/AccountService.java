package com.example.org.service;

import java.util.List;

import java.util.UUID;

import com.example.org.dto.AccountRequestDTO;
import com.example.org.dto.AccountResponseDTO;

public interface AccountService {
	
	public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO);
	
	public List<AccountResponseDTO> getAllAccounts();
	
	public AccountResponseDTO getAccountById(UUID accountId);
	
	public AccountResponseDTO updateAccount(UUID accountId, AccountRequestDTO accountRequestDTO);
	
	public void deleteAccount(UUID accountId);
}
