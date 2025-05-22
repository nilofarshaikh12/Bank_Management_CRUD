package com.example.org.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.org.constants.Constants;
import com.example.org.dto.AccountRequestDTO;
import com.example.org.dto.AccountResponseDTO;
import com.example.org.exceptions.AccountAddFailedException;
import com.example.org.exceptions.AccountNotFoundException;
import com.example.org.exceptions.AccountUpdateFailedException;
import com.example.org.mapper.AccountMapper;
import com.example.org.model.Account;
import com.example.org.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	AccountMapper accountMapper;

	@Override
	public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO) {
		try {
		Account account=accountMapper.toEntity(accountRequestDTO);
		account.setDeleted(false);
		
		Account saved=accountRepository.save(account);
		return accountMapper.toResponseDTO(saved);
		}
		catch(Exception e) {
			throw new AccountAddFailedException(Constants.ACCOUNT_ADD_FAILED);
		}
	}

	
	@Override
	public List<AccountResponseDTO> getAllAccounts() {
		
		List<Account> accounts=accountRepository.findByIsDeletedFalse();
		List<AccountResponseDTO> list=new ArrayList<>();
		for(Account acc:accounts)
		{
			list.add(accountMapper.toResponseDTO(acc));
		}
		
		if(list.isEmpty())
		{
			throw new AccountNotFoundException(Constants.ACCOUNT_NOT_FOUND);
		}
		return list;
	}

	
	@Override
	public AccountResponseDTO getAccountById(UUID accountId) {
		
		Account account=accountRepository.findByAccountIdAndIsDeletedFalse(accountId);
		
		if(account==null)
		{
			throw new AccountNotFoundException(Constants.ACCOUNT_NOT_FOUND);
		}
		
		return accountMapper.toResponseDTO(account);
	}

	
	@Override
	public AccountResponseDTO updateAccount(UUID accountId, AccountRequestDTO accountRequestDTO) {
		
		Account account=accountRepository.findByAccountIdAndIsDeletedFalse(accountId);
		
		if(account==null) {
			throw new AccountUpdateFailedException(Constants.ACCOUNT_UPDATE_FAILED);
		}
		
		account.setCustomerMobileNumber(accountRequestDTO.getCustomerMobileNumber());
		account.setCustomerEmail(accountRequestDTO.getCustomerEmail());
		account.setCustomerAddress(accountRequestDTO.getCustomerAddress());
		
		accountRepository.save(account);
		
		return accountMapper.toResponseDTO(account);
	}

	
	@Override
	public void deleteAccount(UUID accountId) {
		Account account=accountRepository.findByAccountIdAndIsDeletedFalse(accountId);
		account.setDeleted(true);	
		accountRepository.save(account);
		
	}
	
	
}
