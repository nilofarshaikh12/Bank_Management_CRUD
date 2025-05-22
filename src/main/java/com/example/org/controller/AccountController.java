package com.example.org.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.org.dto.AccountRequestDTO;
import com.example.org.dto.AccountResponseDTO;
import com.example.org.service.AccountService;

import jakarta.validation.Valid;

@RestController
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@PostMapping("/add")
	public String createAccount(@RequestBody AccountRequestDTO accountRequestDTO)
	{
		accountService.createAccount(accountRequestDTO);
		
		return "Account created successfully.....";
	}
	
	@GetMapping("/")
	public List<AccountResponseDTO> getAllAccounts()
	{
		return accountService.getAllAccounts();
	}
	
	@GetMapping("/{accountId}")
	public AccountResponseDTO getAccountById(@PathVariable UUID accountId)
	{
		return accountService.getAccountById(accountId);
	}
	
	@PutMapping("/updateAccount/{accountId}")
	public String updateAccount(@PathVariable UUID accountId, @RequestBody AccountRequestDTO accountRequestDTO)
	{
		accountService.updateAccount(accountId, accountRequestDTO);
		return "Account updated successfully...";
	}
	
	@DeleteMapping("/deleteAccount/{accountId}")
	public String deleteAccount(@PathVariable UUID accountId)
	{
		accountService.deleteAccount(accountId);
		return "Account deleted successfully....";
	}
}
