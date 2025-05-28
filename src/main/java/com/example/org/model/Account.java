package com.example.org.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID accountId;
	private String customerName;
	private String customerMobileNumber;
	private String customerEmail;
	private String customerAddress;

	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	private double balance;
	
	private boolean isDeleted = false;

	
	public Account(UUID accountId, String customerName, String customerMobileNumber, String customerEmail,
			String customerAddress, AccountType accountType, double balance, boolean isDeleted) {
		super();
		this.accountId = accountId;
		this.customerName = customerName;
		this.customerMobileNumber = customerMobileNumber;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.accountType = accountType;
		this.balance = balance;
		this.isDeleted = isDeleted;
	}
	
	

	public Account() {
		super();
	}



	public UUID getAccountId() {
		return accountId;
	}

	public void setAccountId(UUID accountId) {
		this.accountId = accountId;
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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
