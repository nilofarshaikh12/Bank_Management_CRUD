package com.example.org.dto;

import java.util.UUID;

import com.example.org.model.AccountType;

public class AccountResponseDTO {

	private UUID accountId;
	private String customerName;
	private String customerMobileNumber;
	private String customerEmail;
	private String customerAddress;
	private AccountType accountType;
	private double balance;

	
	public AccountResponseDTO() {
		super();
	}

	public AccountResponseDTO(UUID accountId, String customerName, String customerMobileNumber, String customerEmail,
			String customerAddress, AccountType accountType, double balance) {
		super();
		this.accountId = accountId;
		this.customerName = customerName;
		this.customerMobileNumber = customerMobileNumber;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.accountType = accountType;
		this.balance = balance;
	}

	
	@Override
	public String toString() {
		return "AccountResponseDTO [accountId=" + accountId + ", customerName=" + customerName
				+ ", customerMobileNumber=" + customerMobileNumber + ", customerEmail=" + customerEmail
				+ ", customerAddress=" + customerAddress + ", accountType=" + accountType + ", balance=" + balance
				+ "]";
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
}
