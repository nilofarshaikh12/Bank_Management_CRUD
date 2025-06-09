package com.example.org.dto;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.example.org.model.AccountType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class AccountRequestDTOTest {
	
	private static Validator validator;
	
	@BeforeAll
	static void setupValidator() {
		ValidatorFactory factory=Validation.buildDefaultValidatorFactory();
		validator=factory.getValidator();
	}

	private void assertFieldInvalid(String fieldName, AccountRequestDTO dto) {
		Set<ConstraintViolation<AccountRequestDTO>> violations= validator.validate(dto);
		assertFalse(violations.isEmpty(), "Expected violations but got none");
		assertTrue(
				violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals(fieldName)),
				"Expected violations for field: "+fieldName
				);
	}
	
	
	private AccountRequestDTO buildValidDTO() {
		return new AccountRequestDTO("Roy", "1234567893", "Roy@gmail.com", 
				                           "Pune", AccountType.CURRENT, 10000.0);
	}
	
	@Test
	void testCustomerName_blank_shouldFail() {
		AccountRequestDTO dto=buildValidDTO();
		dto.setCustomerName("");
		assertFieldInvalid("customerName", dto);
	}
	
	@Test
	void testInvalidCustomerMobileNumber_shouldFail() {
		String[] invalidMob= {
				null,"12345","1234567891234"
		};
		for(String invalid:invalidMob) {
			AccountRequestDTO dto=buildValidDTO();
			dto.setCustomerMobileNumber(invalid);
			assertFieldInvalid("customerMobileNumber", dto);
		}
	}
	@Test
	void testCustomerEmail_invalidFormat_shouldFail() {
		AccountRequestDTO dto=buildValidDTO();
		dto.setCustomerEmail("invalidemail");
		assertFieldInvalid("customerEmail", dto);
	}
	
	@Test
	void testCustomerAddress_empty_shouldFail() {
		AccountRequestDTO dto=buildValidDTO();
		dto.setCustomerAddress("");
		assertFieldInvalid("customerAddress", dto);
	}
	
	@Test
	void testNegativeBalance_shouldFail() {
		AccountRequestDTO dto=buildValidDTO();
		dto.setBalance(-450.0);
		assertFieldInvalid("balance", dto);
	}
	
	@Test
	void testAllValid_shouldPass() {
		AccountRequestDTO dto=buildValidDTO();
		Set<ConstraintViolation<AccountRequestDTO>> violations=validator.validate(dto);
		assertTrue(violations.isEmpty(),"Expected no violations for valid DTO.");
	}
	
}
