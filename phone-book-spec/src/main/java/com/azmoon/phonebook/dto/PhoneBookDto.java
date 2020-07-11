package com.azmoon.phonebook.dto;


import lombok.Data;

import java.util.List;

@Data
public class PhoneBookDto {

	private String phoneNumber;
	private String name;
	private String email;
	private String organization;
	private String github;
	private List<String> repository;
	private StatusDto status;
}
