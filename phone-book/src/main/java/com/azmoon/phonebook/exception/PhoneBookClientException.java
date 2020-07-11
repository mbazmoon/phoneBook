package com.azmoon.phonebook.exception;

import com.azmoon.phonebook.response.ResponseMessage;

public class PhoneBookClientException extends ServiceException {
	private static final long serialVersionUID = -7399013600228794175L;

	private String pspErrorCode;

	private String pspErrorMessage;
	private ResponseMessage resultStatus;

	public PhoneBookClientException(String message) {
		super(message);
	}

	public PhoneBookClientException(ResponseMessage resultStatus) {
		this(resultStatus, null, null, null);
	}

	public PhoneBookClientException(ResponseMessage resultStatus, String pspErrorCode, String pspErrorMessage) {
		this(resultStatus, pspErrorCode, pspErrorMessage, null);
	}

	public PhoneBookClientException(ResponseMessage resultStatus, String pspErrorCode, String pspErrorMessage,
									Throwable cause) {
		super(resultStatus.toString(), cause);
		this.pspErrorCode = pspErrorCode;
		this.pspErrorMessage = pspErrorMessage;
		this.resultStatus = resultStatus;
	}

	@Override
	public ResponseMessage getResultStatus() {
		return resultStatus;
	}
}
