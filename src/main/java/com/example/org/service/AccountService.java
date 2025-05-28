package com.example.org.service;

import java.util.List;

import java.util.UUID;

import com.example.org.dto.AccountRequestDTO;
import com.example.org.dto.AccountResponseDTO;
import com.example.org.exceptions.ResponseMessage;

public interface AccountService {
	
	 ResponseMessage<AccountResponseDTO> createAccount(AccountRequestDTO accountRequestDTO);
	
	 ResponseMessage<List<AccountResponseDTO>> getAllAccounts();
	
	 ResponseMessage<AccountResponseDTO> getAccountById(UUID accountId);
	
	 ResponseMessage<AccountResponseDTO> updateAccount(UUID accountId, AccountRequestDTO accountRequestDTO);
	
	 ResponseMessage<Void> deleteAccount(UUID accountId);
}
