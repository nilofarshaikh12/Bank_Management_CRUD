package com.example.org.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.org.dto.AccountRequestDTO;
import com.example.org.dto.AccountResponseDTO;
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
		
		Account account=accountMapper.toEntity(accountRequestDTO);
		account.setDeleted(false);
		
		Account saved=accountRepository.save(account);
		return accountMapper.toResponseDTO(saved);
	}

	@Override
	public List<AccountResponseDTO> getAllAccounts() {
		
		List<Account> accounts=accountRepository.findByIsDeletedFalse();
		List<AccountResponseDTO> list=new ArrayList<>();
		for(Account acc:accounts)
		{
			list.add(accountMapper.toResponseDTO(acc));
		}
		return list;
	}

	@Override
	public AccountResponseDTO getAccountById(UUID accountId) {
		
		Account account=accountRepository.findByAccountIdAndIsDeletedFalse(accountId);
		return accountMapper.toResponseDTO(account);
	}

	@Override
	public AccountResponseDTO updateAccount(UUID accountId, AccountRequestDTO accountRequestDTO) {
		
		Account account=accountRepository.findByAccountIdAndIsDeletedFalse(accountId);
		
		account.setCustomerMobileNumber(accountRequestDTO.getCustomerMobileNumber());
		account.setCustomerEmail(accountRequestDTO.getCustomerEmail());
		account.setCustomerAddress(accountRequestDTO.getCustomerAddress());
		
		Account updated=accountRepository.save(account);
		
		return accountMapper.toResponseDTO(account);
	}

	@Override
	public void deleteAccount(UUID accountId) {
		Account account=accountRepository.findByAccountIdAndIsDeletedFalse(accountId);
		account.setDeleted(true);
		accountRepository.save(account);
		
	}
	
	
}
