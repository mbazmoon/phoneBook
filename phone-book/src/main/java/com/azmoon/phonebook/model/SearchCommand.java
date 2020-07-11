package com.azmoon.phonebook.model;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class SearchCommand extends BaseEntity {

	private String phoneNumber;

	private String name;

	private String email;

	private String organization;

	private String github;

}
