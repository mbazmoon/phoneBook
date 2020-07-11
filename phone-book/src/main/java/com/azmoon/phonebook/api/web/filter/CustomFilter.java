package com.azmoon.phonebook.api.web.filter;

import com.azmoon.phonebook.response.GeneralResponse;
import com.azmoon.phonebook.response.ResponseMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidParameterException;

@Slf4j
@Component
@Order(1)
public class CustomFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest request = (HttpServletRequest) req;
			chain.doFilter(request, res);
		} catch (InvalidParameterException e) {
			logger.error("invalid request: {}", e.getMessage());
			HttpServletResponse response = (HttpServletResponse) res;
			response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
			response.setCharacterEncoding("UTF-8");
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.getWriter().write(convertObjectToJson(new GeneralResponse(ResponseMessage.BAD_PARAM)));
		}
	}

	private String convertObjectToJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

	@Override
	public void destroy() {
	}
}
