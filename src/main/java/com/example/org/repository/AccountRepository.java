package com.example.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;
import com.example.org.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,UUID> {

	Account findByAccountIdAndIsDeletedFalse(UUID accountId);
	
	List<Account> findByIsDeletedFalse();
	
	boolean existsByCustomerMobileNumberAndAccountIdAndIsDeletedFalse(String customerMobileNumber, UUID accountId);
	
	boolean existsByCustomerEmailAndAccountIdAndIsDeletedFalse(String customerEmail, UUID accountId);
	
	boolean existsByCustomerMobileNumberAndIsDeletedFalse(String customerMobileNumber);
	
	boolean existsByCustomerEmailAndIsDeletedFalse(String customerEmail);
}
