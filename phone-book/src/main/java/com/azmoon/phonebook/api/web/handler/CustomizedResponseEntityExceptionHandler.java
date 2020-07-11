package com.azmoon.phonebook.api.web.handler;

import com.azmoon.phonebook.exception.ServiceException;
import com.azmoon.phonebook.response.GeneralResponse;
import com.azmoon.phonebook.response.ResponseMessage;
import com.azmoon.phonebook.response.ResponseService;
import com.azmoon.phonebook.response.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.InvalidParameterException;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private final Environment environment;

	@NonNull
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex, @NonNull HttpHeaders headers,
																  @NonNull HttpStatus status, @NonNull WebRequest request) {
		logger.error("validation exception {}", ex.getBindingResult().toString());

		return new ResponseEntity<>(new GeneralResponse(invalidResponse(ResponseMessage.BAD_PARAM,
				parseMessage(ex))), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(ServiceException.class)
	public final ResponseEntity<ResponseService> handleBusinessException(ServiceException ex, WebRequest request) {
		logger.error(ex.getResultStatus().getDescription(), ex);
		return new ResponseEntity<>(new GeneralResponse(ex.getResultStatus()), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(InvalidParameterException.class)
	public final ResponseEntity<ResponseService> handleInvalidParameterException(InvalidParameterException ex,
																				 WebRequest request) {
		logger.error(ResponseMessage.BAD_PARAM.getDescription(), ex);
		return new ResponseEntity<>(new GeneralResponse(ResponseMessage.BAD_PARAM),
				HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(Throwable.class)
	public final ResponseEntity<ResponseService> handleGeneralException(Throwable throwable) {
		logger.error(ResponseMessage.FAILURE.getDescription(), throwable);
		logger.error("general exception {}", throwable);
		return new ResponseEntity<>(new GeneralResponse(ResponseMessage.FAILURE), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute("javax.servlet.error.exception", ex, 0);
		}

		return new ResponseEntity<>(new GeneralResponse(invalidResponse(ResponseMessage.FAILURE, ex.getMessage())), headers, status);
	}

	private String parseMessage(MethodArgumentNotValidException ex) {
		StringBuilder sb = new StringBuilder();

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			if (!StringUtils.isEmpty(error.getDefaultMessage())) {
				sb.append(getCustomMessage(error)).append("\r\n");
			}
		}
		return sb.toString().trim();
	}

	private Result invalidResponse(ResponseMessage status, String message) {
		Result result = new Result();

		result.setMessage(message);
		result.setStatus(status.getStatus());
		result.setTitle(status);

		return result;
	}

	private String getCustomMessage(ObjectError error) {
		if (!StringUtils.isEmpty(error.getDefaultMessage())) {
			String customMessage = environment.getProperty(error.getDefaultMessage());
			if (!StringUtils.isEmpty(customMessage))
				return customMessage;
		}
		return error.getDefaultMessage();
	}
}
