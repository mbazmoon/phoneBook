package com.azmoon.phonebook.exception;

import com.azmoon.phonebook.response.ResponseMessage;

public class DuplicatePhoneNumberException extends ServiceException {

	public DuplicatePhoneNumberException(String message) {
		super(message);
	}

	@Override
	public ResponseMessage getResultStatus() {
		return ResponseMessage.DUPLICATE;
	}
}
