package com.example.org.controller;

import java.util.List;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.org.constants.Constants;
import com.example.org.dto.AccountRequestDTO;
import com.example.org.dto.AccountResponseDTO;
import com.example.org.exceptions.ResponseMessage;
import com.example.org.service.AccountService;
import jakarta.validation.Valid;


@RestController
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@PostMapping("/add")
	public ResponseEntity<ResponseMessage<Void>> createAccount(@Valid @RequestBody AccountRequestDTO accountRequestDTO){
		accountService.createAccount(accountRequestDTO);
		ResponseMessage<Void> response=new ResponseMessage<>(Constants.ACCOUNT_CREATED,HttpStatus.CREATED.value());
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/")
	public ResponseEntity <ResponseMessage<List<AccountResponseDTO>>> getAllAccounts(){
		List<AccountResponseDTO> allAccounts = accountService.getAllAccounts();
			ResponseMessage<List<AccountResponseDTO>> response=new ResponseMessage<>(Constants.ACCOUNTS_FETCHED,HttpStatus.OK.value(),allAccounts);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@GetMapping("/{accountId}")
	public ResponseEntity <ResponseMessage<AccountResponseDTO>> getAccountById(@PathVariable UUID accountId){
		AccountResponseDTO account = accountService.getAccountById(accountId);
		ResponseMessage<AccountResponseDTO> response=new ResponseMessage<>(Constants.ACCOUNT_FETCHED, HttpStatus.OK.value(),account);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@PatchMapping("/updateAccount/{accountId}")
	public ResponseEntity<ResponseMessage<Void>> updateAccount(@PathVariable UUID accountId, @RequestBody AccountRequestDTO accountRequestDTO){
		accountService.updateAccount(accountId, accountRequestDTO);
		ResponseMessage<Void> response=new ResponseMessage<>(Constants.ACCOUNT_UPDATED, HttpStatus.OK.value());
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteAccount/{accountId}")
	public ResponseEntity<ResponseMessage<Void>> deleteAccount(@PathVariable UUID accountId){
		accountService.deleteAccount(accountId);
		ResponseMessage<Void> response=new ResponseMessage<>(Constants.ACCOUNT_DELETED, HttpStatus.OK.value());
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
