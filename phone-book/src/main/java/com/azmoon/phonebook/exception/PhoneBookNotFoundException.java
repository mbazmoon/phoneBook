package com.azmoon.phonebook.exception;

import com.azmoon.phonebook.response.ResponseMessage;

public class PhoneBookNotFoundException extends ServiceException {

	public PhoneBookNotFoundException(String message) {
		super(message);
	}

	@Override
	public ResponseMessage getResultStatus() {
		return ResponseMessage.PHONEBOOK_NOT_FOUND;
	}
}
