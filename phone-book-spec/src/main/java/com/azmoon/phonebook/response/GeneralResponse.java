package com.azmoon.phonebook.response;

import lombok.ToString;

@ToString(callSuper = true)
public class GeneralResponse extends ResponseService {

	public GeneralResponse() {
		setResult(ResponseMessage.SUCCESS);
	}

	public GeneralResponse(ResponseMessage responseMessage) {
		setResult(responseMessage);
	}

	public GeneralResponse(Result result) {
		setResult(result);
	}

}
