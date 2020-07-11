package com.azmoon.phonebook.model;


import com.querydsl.core.annotations.QueryEntity;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
@QueryEntity
@Document(collection = "phone_book")
public class PhoneBook extends BaseEntity {

	@Indexed(unique = true)
	private String phoneNumber;

	private String name;

	@Indexed()
	private String email;

	private String organization;

	@Indexed()
	private String github;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private Long creationDate;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private Long lastModifiedDate;

	@Indexed(unique = true)
	private String uid;

	private List<String> repository;


	private Status status = Status.INITIATED;

	public List<String> getRepository() {
		return repository == null ? new ArrayList<>() : repository;
	}

	public Date getCreationDate() {
		if (this.creationDate != null)
			return new Date(creationDate);
		return null;
	}

	public void setCreationDate(Date creationDate) {
		if (creationDate != null)
			this.creationDate = creationDate.getTime();
	}

	public Date getLastModifiedDate() {
		if (this.lastModifiedDate != null)
			return new Date(lastModifiedDate);
		return null;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		if (lastModifiedDate != null)
			this.lastModifiedDate = lastModifiedDate.getTime();
	}

}
