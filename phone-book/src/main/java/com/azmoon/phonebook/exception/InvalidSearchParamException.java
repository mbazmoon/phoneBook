package com.azmoon.phonebook.exception;

import com.azmoon.phonebook.response.ResponseMessage;

public class InvalidSearchParamException extends ServiceException {

	public InvalidSearchParamException(String message) {
		super(message);
	}

	@Override
	public ResponseMessage getResultStatus() {
		return ResponseMessage.PHONEBOOK_NOT_FOUND;
	}
}
