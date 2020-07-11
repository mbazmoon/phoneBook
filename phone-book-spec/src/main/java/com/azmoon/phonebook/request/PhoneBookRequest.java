package com.azmoon.phonebook.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneBookRequest {
	@NotBlank
	String PhoneNumber;

	String github;

	String name;

	String email;

	String organization;


}
