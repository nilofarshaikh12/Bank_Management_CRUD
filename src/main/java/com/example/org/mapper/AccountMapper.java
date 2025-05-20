package com.example.org.mapper;

import org.mapstruct.Mapper;
import com.example.org.dto.AccountRequestDTO;
import com.example.org.dto.AccountResponseDTO;
import com.example.org.model.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {

	
	Account toEntity(AccountRequestDTO accountRequestDTO);
	AccountResponseDTO toResponseDTO(Account account);
	
}
