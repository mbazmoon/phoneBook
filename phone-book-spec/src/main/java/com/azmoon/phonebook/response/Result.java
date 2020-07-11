package com.azmoon.phonebook.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class Result implements Serializable {

	private static final long serialVersionUID = 6091567334208093240L;

	private ResponseMessage title;

	private int status;

	private String message;

	private ResultLevel level;

	public Result(ResponseMessage title) {
		this.title = title;
		this.status = title.getStatus();
		this.message = title.getDescription();
		this.level = getResultLevel(title);
	}

	public Result(ResponseMessage title, ResultLevel level) {
		this.title = title;
		this.status = title.getStatus();
		this.message = title.getDescription();
		this.level = level;
	}

	public ResultLevel getResultLevel(ResponseMessage responseMessage) {
		if (responseMessage == ResponseMessage.SUCCESS)
			return ResultLevel.INFO;
		else if (responseMessage == ResponseMessage.FAILURE)
			return ResultLevel.BLOCKER;
		else return ResultLevel.WARN;
	}

}
