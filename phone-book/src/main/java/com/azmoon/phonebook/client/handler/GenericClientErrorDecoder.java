package com.azmoon.phonebook.client.handler;

import com.azmoon.phonebook.exception.PhoneBookClientException;
import com.azmoon.phonebook.response.ResponseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.security.InvalidParameterException;

@Slf4j
public class GenericClientErrorDecoder extends ErrorDecoder.Default {

	private final ObjectMapper objectMapper;

	public GenericClientErrorDecoder() {
		this.objectMapper = new ObjectMapper();
	}

	@Override
	public Exception decode(String methodKey, Response response) {
		if (HttpStatus.BAD_REQUEST.value() == response.status())
			return new InvalidParameterException();
		ResponseService responseService = read(response);
		if (responseService != null) {
			return new PhoneBookClientException(responseService.getResult().getTitle());
		}

		return super.decode(methodKey, response);
	}

	private ResponseService read(Response response) {
		try {
			return objectMapper.readValue(Util.toString(response.body().asReader()), ResponseService.class);
		} catch (IOException e) {
			logger.error("couldn't parse general response: {}", response);
			return null;
		}
	}
}
