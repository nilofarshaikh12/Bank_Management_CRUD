package com.example.org.exceptions;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage<T> {

	private String message;
	private int status;
	private LocalDateTime timestamp;
	private T data;
	
	public ResponseMessage(String message,int status, T data)
	{
		this.message=message;
		this.status=status;
		this.data=data;
		this.timestamp=LocalDateTime.now();
	}
	
	public ResponseMessage(String message,int status) {
		this(message,status,null);
	}
	
	
	public String getMessage()
	{
		return message;
	}
	
	public int getStatus()
	{
		return status;
	}
	
	public LocalDateTime getTimestamp()
	{
		return timestamp;
	}

	public T getData() {
		return data;
	}
}
