package com.example.org.service;

import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.org.constants.Constants;
import com.example.org.converter.AccountConverter;
import com.example.org.dto.AccountRequestDTO;
import com.example.org.dto.AccountResponseDTO;
import com.example.org.exceptions.AccountAddFailedException;
import com.example.org.exceptions.AccountDeleteFailedException;
import com.example.org.exceptions.AccountNotFoundException;
import com.example.org.exceptions.AccountUpdateFailedException;
import com.example.org.exceptions.ResponseMessage;
import com.example.org.model.Account;
import com.example.org.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AccountConverter accountConverter;
	
	@Override
	public ResponseMessage<AccountResponseDTO> createAccount(AccountRequestDTO accountRequestDTO) {
		try {
			Account account = accountConverter.toEntity(accountRequestDTO);
			account.setDeleted(false);
		    Account saved=accountRepository.save(account);
		    AccountResponseDTO accountResponseDTO=accountConverter.toResponseDTO(saved);
			return new ResponseMessage<>(Constants.ACCOUNT_CREATED,HttpStatus.CREATED.value(),accountResponseDTO);
		} catch (Exception ex) {
			return new ResponseMessage<>(Constants.ACCOUNT_ADD_FAILED, HttpStatus.BAD_REQUEST.value());
		}
	}

	@Override
	public ResponseMessage<List<AccountResponseDTO>> getAllAccounts() {
		try {
			List<Account> accounts = accountRepository.findByIsDeletedFalse();
			List<AccountResponseDTO> list = new ArrayList<>();
			for (Account acc : accounts) {
				list.add(accountConverter.toResponseDTO(acc));
			}
			if (list.isEmpty()) {
				return new ResponseMessage<>(Constants.ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND.value());
			}
			return new ResponseMessage<>(Constants.ACCOUNTS_FETCHED, HttpStatus.OK.value(),list);
		}catch(Exception ex){
			return new ResponseMessage<>(Constants.ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND.value());
		}
	}

	@Override
	public ResponseMessage<AccountResponseDTO> getAccountById(UUID accountId) {
		try{
			Account account = accountRepository.findByAccountIdAndIsDeletedFalse(accountId);
			
			if(account==null) {
				return new ResponseMessage<>(Constants.ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND.value());
			}
			AccountResponseDTO accountResponseDTO= accountConverter.toResponseDTO(account);
			return new ResponseMessage<>(Constants.ACCOUNT_FETCHED, HttpStatus.OK.value(),accountResponseDTO);
		}catch(Exception ex) {
			return new ResponseMessage<>(Constants.ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND.value());
		}
	}
	
	@Override
	public ResponseMessage<AccountResponseDTO> updateAccount(UUID accountId, AccountRequestDTO accountRequestDTO) {
		try {
			Account account = accountRepository.findByAccountIdAndIsDeletedFalse(accountId);
			if (account == null) {
				return new ResponseMessage<>(Constants.ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND.value());
			}
			account.setCustomerMobileNumber(accountRequestDTO.getCustomerMobileNumber());
			account.setCustomerEmail(accountRequestDTO.getCustomerEmail());
			account.setCustomerAddress(accountRequestDTO.getCustomerAddress());
			accountRepository.save(account);
			AccountResponseDTO accountResponseDTO= accountConverter.toResponseDTO(account);
			return new ResponseMessage<>(Constants.ACCOUNT_UPDATED,HttpStatus.OK.value(),accountResponseDTO);
		}catch(Exception ex) {
			return new ResponseMessage<>(Constants.ACCOUNT_UPDATE_FAILED, HttpStatus.BAD_REQUEST.value());
		}
	}
	
	@Override
	public ResponseMessage<Void> deleteAccount(UUID accountId) {
		try {
			Account account = accountRepository.findByAccountIdAndIsDeletedFalse(accountId);
			if (account == null) {
				return new ResponseMessage<>(Constants.ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND.value());
			}
			account.setDeleted(true);
			accountRepository.save(account);
			return new ResponseMessage<>(Constants.ACCOUNT_DELETED, HttpStatus.OK.value());
		}catch(Exception ex) {
			return new ResponseMessage<>(Constants.ACCOUNT_DELETE_FAILED, HttpStatus.BAD_REQUEST.value());
		}
	}

}
