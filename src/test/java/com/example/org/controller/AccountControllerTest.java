package com.example.org.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.org.constants.Constants;
import com.example.org.converter.AccountConverter;
import com.example.org.dto.AccountRequestDTO;
import com.example.org.dto.AccountResponseDTO;
import com.example.org.exceptions.ResponseMessage;
import com.example.org.model.Account;
import com.example.org.model.AccountType;
import com.example.org.service.AccountService;

public class AccountControllerTest {

	@InjectMocks
	private AccountController accountController;
	
	@Mock
	private AccountService accountService;
	
	private AccountConverter accountConverter;
	private AccountResponseDTO accountResponseDTO;
	private AccountRequestDTO accountRequestDTO;
	private UUID accountId;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		accountConverter=new AccountConverter();
		accountId=UUID.randomUUID();
	    accountRequestDTO=new AccountRequestDTO("John", "4367685943", "john@gmail.com", "Mumbai", AccountType.CURRENT, 20000);
	    Account account=accountConverter.toEntity(accountRequestDTO);
	    account.setAccountId(accountId);
	    accountResponseDTO=accountConverter.toResponseDTO(account);
	}
	
	@Test
	void testCreateAccount() {
		ResponseMessage<AccountResponseDTO> responseMessage=new ResponseMessage<>(Constants.ACCOUNT_CREATED,HttpStatus.CREATED.value(),accountResponseDTO);
		when(accountService.createAccount(any())).thenReturn(responseMessage);
		
		ResponseEntity<ResponseMessage<AccountResponseDTO>> response=accountController.createAccount(accountRequestDTO);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(Constants.ACCOUNT_CREATED, response.getBody().getMessage());
	}
	
	@Test
	void testGetAllAccounts() {
		List<AccountResponseDTO> list=Collections.singletonList(accountResponseDTO);
		ResponseMessage<List<AccountResponseDTO>> responseMessage=new ResponseMessage<>(Constants.ACCOUNTS_FETCHED,HttpStatus.OK.value(),list);
		when(accountService.getAllAccounts()).thenReturn(responseMessage);
		
		ResponseEntity<ResponseMessage<List<AccountResponseDTO>>> response=accountController.getAllAccounts();
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody().getData());
		assertEquals(1,response.getBody().getData().size());
	}
	
	@Test
	void testGetAccountById() {
		ResponseMessage<AccountResponseDTO> responseMessage=new ResponseMessage<>(Constants.ACCOUNT_FETCHED, HttpStatus.OK.value(),accountResponseDTO);
		when(accountService.getAccountById(accountId)).thenReturn(responseMessage);
		
		ResponseEntity<ResponseMessage<AccountResponseDTO>> response=accountController.getAccountById(accountId);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(accountResponseDTO, response.getBody().getData());
	}
	
	@Test
	void testUpdateAccount() {
		ResponseMessage<AccountResponseDTO> responseMessage=new ResponseMessage<>(Constants.ACCOUNT_UPDATED, HttpStatus.OK.value(),accountResponseDTO);
		when(accountService.updateAccount(eq(accountId), any())).thenReturn(responseMessage);
		
		ResponseEntity<ResponseMessage<AccountResponseDTO>> response=accountController.updateAccount(accountId, accountRequestDTO);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(accountResponseDTO, response.getBody().getData());
	}
	
	@Test
	void testDeleteAccount() {
		ResponseMessage<Void> responseMessage=new ResponseMessage<>(Constants.ACCOUNT_DELETED, HttpStatus.OK.value());
		when(accountService.deleteAccount(accountId)).thenReturn(responseMessage);
		
		ResponseEntity<ResponseMessage<Void>> response=accountController.deleteAccount(accountId);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(Constants.ACCOUNT_DELETED, response.getBody().getMessage());
	}
	
}
