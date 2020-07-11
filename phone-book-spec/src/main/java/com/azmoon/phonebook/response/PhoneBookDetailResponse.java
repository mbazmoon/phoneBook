package com.azmoon.phonebook.response;

import lombok.Data;

@Data
public class PhoneBookDetailResponse extends  ResponseService {

	String PhoneNumber;

	String github;

	String name;

	String email;

	String organization;
}
