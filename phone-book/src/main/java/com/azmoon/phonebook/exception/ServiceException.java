package com.azmoon.phonebook.exception;


import com.azmoon.phonebook.response.ResponseMessage;

public abstract class ServiceException extends Exception {

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public abstract ResponseMessage getResultStatus();

}
