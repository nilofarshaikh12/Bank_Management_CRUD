package com.example.org.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.org.constants.Constants;
import com.example.org.converter.AccountConverter;
import com.example.org.dto.AccountRequestDTO;
import com.example.org.dto.AccountResponseDTO;
import com.example.org.exceptions.ResponseMessage;
import com.example.org.model.Account;
import com.example.org.repository.AccountRepository;
import org.slf4j.LoggerFactory;

@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger logger=LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AccountConverter accountConverter;
	
	@Override
	public ResponseMessage<AccountResponseDTO> createAccount(AccountRequestDTO accountRequestDTO) {
		logger.info("creating account for request: {}",accountRequestDTO);
		try {
			Account account = accountConverter.toEntity(accountRequestDTO);
			account.setDeleted(false);
		    Account saved=accountRepository.save(account);
		    AccountResponseDTO accountResponseDTO=accountConverter.toResponseDTO(saved);
		    logger.info("Account created successfully with id: {}",saved.getAccountId());
			return new ResponseMessage<>(Constants.ACCOUNT_CREATED,HttpStatus.CREATED.value(),accountResponseDTO);
		} catch (Exception ex) {
			logger.error("Failed to create account for request: {}. Error: {}",accountRequestDTO);
			return new ResponseMessage<>(Constants.ACCOUNT_ADD_FAILED, HttpStatus.BAD_REQUEST.value());
		}
	}

	@Override
	public ResponseMessage<List<AccountResponseDTO>> getAllAccounts() {
		logger.info("Fetching all non-deleted accounts");
		try {
			List<Account> accounts = accountRepository.findByIsDeletedFalse();
			logger.debug("Found {} accounts",accounts.size());
			List<AccountResponseDTO> list = new ArrayList<>();
			for (Account acc : accounts) {
				list.add(accountConverter.toResponseDTO(acc));
			}
			if (list.isEmpty()) {
				logger.warn("No accounts found");
				return new ResponseMessage<>(Constants.ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND.value());
			}
			logger.info("Accounts fetched successfully");
			return new ResponseMessage<>(Constants.ACCOUNTS_FETCHED, HttpStatus.OK.value(),list);
		}catch(Exception ex){
			logger.error("Exception occur while fetching account",ex);
			return new ResponseMessage<>(Constants.ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND.value());
		}
	}

	@Override
	public ResponseMessage<AccountResponseDTO> getAccountById(UUID accountId) {
		logger.info("Fetching account for id: {}",accountId);
		try{
			Account account = accountRepository.findByAccountIdAndIsDeletedFalse(accountId);
			if(account==null) {
				logger.warn("Account not found for id: {}",accountId);
				return new ResponseMessage<>(Constants.ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND.value());
			}
			AccountResponseDTO accountResponseDTO= accountConverter.toResponseDTO(account);
			logger.info("Account fetched successfully: {}",accountResponseDTO);
			return new ResponseMessage<>(Constants.ACCOUNT_FETCHED, HttpStatus.OK.value(),accountResponseDTO);
		}catch(Exception ex) {
			logger.error("Exception occur while fetching account with id: {}",accountId,ex);
			return new ResponseMessage<>(Constants.ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND.value());
		}
	}
	
	@Override
	public ResponseMessage<AccountResponseDTO> updateAccount(UUID accountId, AccountRequestDTO accountRequestDTO) {
		logger.info("Updating account with id: {}",accountId);
		try {
			Account account = accountRepository.findByAccountIdAndIsDeletedFalse(accountId);
			if (account == null) {
				logger.warn("Account with id {} not found for updating",accountId);
				return new ResponseMessage<>(Constants.ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND.value());
			}
			logger.debug("Original account data: {}",account);
			logger.debug("Update data: {}",accountRequestDTO);
			account.setCustomerMobileNumber(accountRequestDTO.getCustomerMobileNumber());
			account.setCustomerEmail(accountRequestDTO.getCustomerEmail());
			account.setCustomerAddress(accountRequestDTO.getCustomerAddress());
			accountRepository.save(account);
			AccountResponseDTO accountResponseDTO= accountConverter.toResponseDTO(account);
			logger.info("Account updated successfully: {}",accountResponseDTO);
			return new ResponseMessage<>(Constants.ACCOUNT_UPDATED,HttpStatus.OK.value(),accountResponseDTO);
		}catch(Exception ex) {
			logger.error("Error occur while updating account with id: {}",accountId);
			return new ResponseMessage<>(Constants.ACCOUNT_UPDATE_FAILED, HttpStatus.BAD_REQUEST.value());
		}
	}
	
	@Override
	public ResponseMessage<Void> deleteAccount(UUID accountId) {
		logger.info("Attempting to delete account with id: {}",accountId);
		try {
			Account account = accountRepository.findByAccountIdAndIsDeletedFalse(accountId);
			if (account == null) {
				logger.warn("Account with id {} not found for deletion",accountId);
				return new ResponseMessage<>(Constants.ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND.value());
			}
			account.setDeleted(true);
			accountRepository.save(account);
			logger.info("Account with id {} marked as deleted successfully",accountId);
			return new ResponseMessage<>(Constants.ACCOUNT_DELETED, HttpStatus.OK.value());
		}catch(Exception ex) {
			logger.error("Error occurred while deleting account with id: {}",accountId,ex);
			return new ResponseMessage<>(Constants.ACCOUNT_DELETE_FAILED, HttpStatus.BAD_REQUEST.value());
		}
	}

}
