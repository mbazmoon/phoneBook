package com.azmoon.phonebook.response;

import java.io.IOException;
import java.util.Properties;
public enum ResponseMessage {

	SUCCESS(0, "success"),
	FAILURE(100, "failure"),
	BAD_PARAM(1,"bad.param"),
	DUPLICATE(2,"phone.number.duplicate"),
	PHONEBOOK_NOT_FOUND(3,"not.found.phone.book"),
	INVALID_SEARCH_PARAM(4,"invalid.search.param")
	;
	private final String description;

	private final Integer status;

	private Properties errorMessageProperties;


	ResponseMessage(Integer status, String description) {
		populateConfigProperty();
		this.status = status;
		String errorMsg = errorMessageProperties.getProperty(description);
		if (errorMsg != null)
			this.description = errorMsg;
		else this.description = description;
	}

	private void populateConfigProperty() {
		try {
			errorMessageProperties = new Properties();
			errorMessageProperties.load((this.getClass().getResourceAsStream("/error-messages.properties")));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getDescription() {
		return description;
	}

	public Integer getStatus() {
		return status;
	}


}
