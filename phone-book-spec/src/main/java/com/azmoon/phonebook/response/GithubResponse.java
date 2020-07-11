package com.azmoon.phonebook.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GithubResponse {
	@JsonProperty("name")
	private String name;
}




