package com.example.org.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.org.constants.Constants;
import com.example.org.dto.AccountRequestDTO;
import com.example.org.model.AccountType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AccountControllerIntegrationTest {
		
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private UUID testAccountId;
	
	@BeforeEach
	void setUp() throws Exception{
		AccountRequestDTO request=new AccountRequestDTO("Banti", "7658439823", "banti@gmail.com",
											"Pune", AccountType.CURRENT, 12000);
		String requestJson=objectMapper.writeValueAsString(request);
		
		MvcResult result=mockMvc.perform(post("/add")
							.contentType(MediaType.APPLICATION_JSON)
							.content(requestJson))
							.andExpect(status().isCreated())
							.andReturn();
		
		String responseBody=result.getResponse().getContentAsString();
		JsonNode jsonNode=objectMapper.readTree(responseBody);
		testAccountId= UUID.fromString(jsonNode.get("data").get("accountId").asText());
	}
	
	@Test
	void testGetAccountById() throws Exception {
		mockMvc.perform(get("/" + testAccountId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.accountId").value(testAccountId.toString()));
	}
	
	@Test
	void testGetAllAccounts() throws Exception{
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").isArray());
	}
	
	@Test
	void testUpdateAccount() throws Exception{
		String updatedMobileNumber="1122334455";
		String updatedEmail="banti12@gmail.com";
		String updatedAddress="Mumbai";
		
		AccountRequestDTO updateRequest=new AccountRequestDTO("Joy", updatedMobileNumber, updatedEmail, 
				updatedAddress, AccountType.CURRENT, 12000);
		
		
		mockMvc.perform(patch("/updateAccount/" + testAccountId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updateRequest)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.customerMobileNumber").value(updatedMobileNumber))
				.andExpect(jsonPath("$.data.customerEmail").value(updatedEmail))
				.andExpect(jsonPath("$.data.customerAddress").value(updatedAddress));
	}
	
	@Test
	void testDeleteAccount() throws Exception{
		mockMvc.perform(delete("/deleteAccount/" + testAccountId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value(Constants.ACCOUNT_DELETED));
	}
}
