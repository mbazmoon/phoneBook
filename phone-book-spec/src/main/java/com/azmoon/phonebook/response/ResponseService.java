package com.azmoon.phonebook.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
public class ResponseService {
	private static final long serialVersionUID = 2466742670208814125L;

	private Result result;

	@JsonProperty
	public void setResult(Result result) {
		this.result = result;
	}

	public void setResult(ResponseMessage responseMessage) {
		if (responseMessage == null)
			return;
		Result result = new Result();
		result.setTitle(responseMessage);
		result.setMessage(responseMessage.getDescription());
		result.setStatus(responseMessage.getStatus());
		result.setLevel(result.getResultLevel(responseMessage));
		this.result = result;
	}


	public void setResult(ResponseMessage responseMessage, String message) {
		Result result = new Result();
		result.setTitle(responseMessage);
		result.setMessage(message);
		result.setStatus(responseMessage.getStatus());
		result.setLevel(result.getResultLevel(responseMessage));
		this.result = result;
	}

	public void setResult(ResponseMessage responseMessage, boolean isExternal) {
		if (responseMessage == null)
			return;
		Result result = new Result();
		result.setMessage(responseMessage.getDescription());
		result.setStatus(responseMessage.getStatus());
		if (!isExternal)
			result.setTitle(responseMessage);
		result.setLevel(result.getResultLevel(responseMessage));
		this.result = result;
	}

}
