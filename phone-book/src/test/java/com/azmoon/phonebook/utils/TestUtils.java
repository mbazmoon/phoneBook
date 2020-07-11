package com.azmoon.phonebook.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class TestUtils {

	public static final String AZMOON_API = "/azmoon/";
	public static final String PHONE_BOOK_PATH = "/phone-books";

	public static String baseUrl(int port) {
		return "http://localhost:" + port + AZMOON_API;
	}

	public static HttpHeaders createHttpHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return headers;
	}
}
