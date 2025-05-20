package com.example.org.dto;

import com.example.org.model.AccountType;

import jakarta.validation.constraints.NotEmpty;

public class AccountRequestDTO {

	@NotEmpty
	private String customerName;

	@NotEmpty
	private String customerMobileNumber;

	@NotEmpty
	private String customerEmail;

	@NotEmpty
	private String customerAddress;

	private AccountType accountType;
	private double balance;

	
	public AccountRequestDTO(@NotEmpty String customerName, @NotEmpty String customerMobileNumber,
			@NotEmpty String customerEmail, @NotEmpty String customerAddress, AccountType accountType, double balance) {
		super();
		this.customerName = customerName;
		this.customerMobileNumber = customerMobileNumber;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.accountType = accountType;
		this.balance = balance;
	}

	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobileNumber() {
		return customerMobileNumber;
	}

	public void setCustomerMobileNumber(String customerMobileNumber) {
		this.customerMobileNumber = customerMobileNumber;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
