package com.example.org.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.example.org.constants.Constants;
import com.example.org.converter.AccountConverter;
import com.example.org.dto.AccountRequestDTO;
import com.example.org.dto.AccountResponseDTO;
import com.example.org.exceptions.ResponseMessage;
import com.example.org.model.Account;
import com.example.org.model.AccountType;
import com.example.org.repository.AccountRepository;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
	
	@InjectMocks
	private AccountServiceImpl accountService;
	
	@Mock
	private AccountRepository accountRepository;
	
	@Mock
	private AccountConverter accountConverter;
	
	private AccountRequestDTO accountRequestDTO;
	private AccountResponseDTO accountResponseDTO;
	private Account account;
	
	@BeforeEach
	void setUp() {
		accountRequestDTO= new AccountRequestDTO("Roy", "6754382674", "Roy@gmail.com", "Pune", AccountType.CURRENT, 10000);
		account=new Account();
		account.setAccountId(UUID.randomUUID());
		account.setCustomerName("Roy");
		account.setCustomerMobileNumber("6754382674");
		account.setCustomerEmail("Roy@gmail.com");
		account.setCustomerAddress("Pune");
		account.setAccountType(AccountType.CURRENT);
		account.setBalance(10000);
		account.setDeleted(false);
		
		accountResponseDTO= new AccountResponseDTO();
	}
	
	@Test
	void testCreateAccount_success() {
		when(accountRepository.existsByCustomerMobileNumberAndIsDeletedFalse(anyString())).thenReturn(false);
		when(accountRepository.existsByCustomerEmailAndIsDeletedFalse(anyString())).thenReturn(false);
		when(accountConverter.toEntity(any())).thenReturn(account);
		when(accountRepository.save(any())).thenReturn(account);
		when(accountConverter.toResponseDTO(any())).thenReturn(accountResponseDTO);
		
		ResponseMessage<AccountResponseDTO> result=accountService.createAccount(accountRequestDTO);
		
		assertEquals(HttpStatus.CREATED.value(), result.getStatus());
		assertEquals(Constants.ACCOUNT_CREATED, result.getMessage());
		assertNotNull(result.getData());
	}
	
	@Test
	void testCreateAccount_duplicateMobile() {
		when(accountRepository.existsByCustomerMobileNumberAndIsDeletedFalse(anyString())).thenReturn(true);
		
		ResponseMessage<AccountResponseDTO> result=accountService.createAccount(accountRequestDTO);
		
		assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatus());
		assertEquals(Constants.MOBILE_EXISTS, result.getMessage());
	}
	
	@Test
	void testCreateAccount_duplicateEmail() {
		when(accountRepository.existsByCustomerMobileNumberAndIsDeletedFalse(anyString())).thenReturn(false);
		when(accountRepository.existsByCustomerEmailAndIsDeletedFalse(anyString())).thenReturn(true);
		
		ResponseMessage<AccountResponseDTO> result=accountService.createAccount(accountRequestDTO);
		
		assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatus());
		assertEquals(Constants.EMAIL_EXISTS, result.getMessage());
	}
	
	@Test
	void testCreateAccount_invalidMobileLength() {
		String[] invalidNumbers= {"122334","1234562783189"};
		
		for(String mobile:invalidNumbers) {
			accountRequestDTO.setCustomerMobileNumber(mobile);
			ResponseMessage<AccountResponseDTO> result=accountService.createAccount(accountRequestDTO);
			
			assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatus());
			assertEquals(Constants.INVALID_MOBILE, result.getMessage());
		}
	}
	
	@Test
	void testGetAllAccounts_success() {
		when(accountRepository.findByIsDeletedFalse()).thenReturn(List.of(account));
		when(accountConverter.toResponseDTO(account)).thenReturn(accountResponseDTO);
		
		ResponseMessage<List<AccountResponseDTO>> result=accountService.getAllAccounts();
		
		assertEquals(HttpStatus.OK.value(), result.getStatus());
		assertEquals(Constants.ACCOUNTS_FETCHED, result.getMessage());
		assertFalse(result.getData().isEmpty());
	}
	
	@Test
	void testGetAllAccounts_emptyList() {
		when(accountRepository.findByIsDeletedFalse()).thenReturn(Collections.emptyList());
		
		ResponseMessage<List<AccountResponseDTO>> result=accountService.getAllAccounts();
		assertEquals(HttpStatus.NOT_FOUND.value(), result.getStatus());
		assertEquals(Constants.ACCOUNT_NOT_FOUND, result.getMessage());
	}
	
	@Test
	void testGetAccountById_success() {
		UUID accountId=UUID.randomUUID();
		when(accountRepository.findByAccountIdAndIsDeletedFalse(accountId)).thenReturn(account);
		when(accountConverter.toResponseDTO(account)).thenReturn(accountResponseDTO);
		
		ResponseMessage<AccountResponseDTO> result=accountService.getAccountById(accountId);
		
		assertEquals(HttpStatus.OK.value(), result.getStatus());
		assertEquals(Constants.ACCOUNT_FETCHED, result.getMessage());
	}
	
	@Test
	void testGetAccountById_notFound() {
		UUID accountId=UUID.randomUUID();
		when(accountRepository.findByAccountIdAndIsDeletedFalse(accountId)).thenReturn(null);
		
		ResponseMessage<AccountResponseDTO> result=accountService.getAccountById(accountId);
		
		assertEquals(HttpStatus.NOT_FOUND.value(), result.getStatus());
		assertEquals(Constants.ACCOUNT_NOT_FOUND, result.getMessage());
	}
	
	@Test
	void testUpdateAccount_success() {
		UUID accountId=UUID.randomUUID();
		when(accountRepository.findByAccountIdAndIsDeletedFalse(accountId)).thenReturn(account);
		when(accountRepository.existsByCustomerEmailAndAccountIdAndIsDeletedFalse(anyString(), any())).thenReturn(false);
		when(accountRepository.existsByCustomerMobileNumberAndAccountIdAndIsDeletedFalse(anyString(), any())).thenReturn(false);
		when(accountConverter.toResponseDTO(account)).thenReturn(accountResponseDTO);
		
		ResponseMessage<AccountResponseDTO> result=accountService.updateAccount(accountId, accountRequestDTO);
		
		assertEquals(HttpStatus.OK.value(), result.getStatus());
		assertEquals(Constants.ACCOUNT_UPDATED, result.getMessage());
		verify(accountRepository).save(account);
	}
	
	@Test
	void testUpdateAccount_notFound() {
		UUID accountId=UUID.randomUUID();
		when(accountRepository.findByAccountIdAndIsDeletedFalse(accountId)).thenReturn(null);
		
		ResponseMessage<AccountResponseDTO> result=accountService.updateAccount(accountId, accountRequestDTO);
		
		assertEquals(HttpStatus.NOT_FOUND.value(), result.getStatus());
		assertEquals(Constants.ACCOUNT_NOT_FOUND, result.getMessage());
	}

	@Test
	void testDeleteAccount_success() {
		UUID accountId=UUID.randomUUID();
		when(accountRepository.findByAccountIdAndIsDeletedFalse(accountId)).thenReturn(account);
		
		ResponseMessage<Void> result=accountService.deleteAccount(accountId);
		
		assertEquals(HttpStatus.OK.value(), result.getStatus());
		assertEquals(Constants.ACCOUNT_DELETED, result.getMessage());
		verify(accountRepository).save(account);
	}
	
	@Test
	void testDeleteAccount_notFound() {
		UUID accountId=UUID.randomUUID();
		when(accountRepository.findByAccountIdAndIsDeletedFalse(accountId)).thenReturn(null);
		
		ResponseMessage<Void> result=accountService.deleteAccount(accountId);
		
		assertEquals(HttpStatus.NOT_FOUND.value(), result.getStatus());
		assertEquals(Constants.ACCOUNT_NOT_FOUND, result.getMessage());
	}
}
