package com.example.org.dto;

import com.example.org.model.AccountType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class AccountRequestDTO {

	@NotBlank(message="Customer name is required")
	private String customerName;

	@NotBlank(message="Mobile number is required")
	@Pattern(regexp= "^[0-9]{10}$",message="Mobile number must be exactly 10 digits")
	private String customerMobileNumber;

	@NotBlank(message="email is required")
	@Email(message="invalid email format")
	private String customerEmail;

	@NotEmpty
	private String customerAddress;

	private AccountType accountType;
	
	@Min(value = 0, message = "Balance cannot be negative")
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

	
	@Override
	public String toString() {
		return "AccountRequestDTO [customerName=" + customerName + ", customerMobileNumber=" + customerMobileNumber
				+ ", customerEmail=" + customerEmail + ", customerAddress=" + customerAddress + ", accountType="
				+ accountType + ", balance=" + balance + "]";
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
