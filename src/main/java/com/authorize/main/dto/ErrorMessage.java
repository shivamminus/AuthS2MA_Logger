package com.authorize.main.dto;

import java.util.Date;

/*
		 Error model Example
		JSON:
		{
		  statusCode:500,
		  timestamp: "Tue May 25 17:37:21 UTC 2021",
		  message: "Something went wrong"
		}

*/

public class ErrorMessage 
{
	private int statusCode;
	private Date timestamp;
	private String message;

	public ErrorMessage(int statusCode, Date timestamp, String message) {
		this.statusCode = statusCode;
		this.timestamp = timestamp;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

}
