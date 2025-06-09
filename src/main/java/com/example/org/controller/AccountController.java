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
import com.example.org.dto.AccountRequestDTO;
import com.example.org.dto.AccountResponseDTO;
import com.example.org.exceptions.ResponseMessage;
import com.example.org.service.AccountService;
import jakarta.validation.Valid;


@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@PostMapping("/add")
	public ResponseEntity<ResponseMessage<AccountResponseDTO>> createAccount(@Valid @RequestBody AccountRequestDTO accountRequestDTO){
			ResponseMessage<AccountResponseDTO> response=accountService.createAccount(accountRequestDTO);
			return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatus()));
	}
	
	
	@GetMapping("/")
	public ResponseEntity <ResponseMessage<List<AccountResponseDTO>>> getAllAccounts(){
		ResponseMessage<List<AccountResponseDTO>> response=accountService.getAllAccounts();
		return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatus()));
	}
	
	
	@GetMapping("/{accountId}")
	public ResponseEntity <ResponseMessage<AccountResponseDTO>> getAccountById(@PathVariable UUID accountId){
		ResponseMessage<AccountResponseDTO> response= accountService.getAccountById(accountId);
		return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatus()));
	}
	
	
	@PatchMapping("/updateAccount/{accountId}")
	public ResponseEntity<ResponseMessage<AccountResponseDTO>> updateAccount(@PathVariable UUID accountId,@RequestBody AccountRequestDTO accountRequestDTO){
		ResponseMessage<AccountResponseDTO> response=accountService.updateAccount(accountId, accountRequestDTO);
		return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatus()));
	}
	
	
	@DeleteMapping("/deleteAccount/{accountId}")
	public ResponseEntity<ResponseMessage<Void>> deleteAccount(@PathVariable UUID accountId){
		ResponseMessage<Void> response=accountService.deleteAccount(accountId);
		return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatus()));
	}
}
