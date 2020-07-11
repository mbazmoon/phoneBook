package com.azmoon.phonebook.model;

import com.azmoon.phonebook.exception.InvalidSearchParamException;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.stereotype.Component;

@Component
public class PredicatePhoneBook {
	private QPhoneBook qPhoneBook = QPhoneBook.phoneBook;
	private BooleanExpression anyBoolean =qPhoneBook.isNull();

	public BooleanExpression findByName(String name) {
		if (name == null) {
			return anyBoolean;
		}
		return qPhoneBook.name.like("%" + name + "%");
	}

	public BooleanExpression findByEmail(String email) {
		if (email == null) {
			return anyBoolean;
		}
		return qPhoneBook.email.eq(email.toLowerCase());
	}

	public BooleanExpression findByPhoneNumber(String phoneNumber) {
		if (phoneNumber == null) {
			return anyBoolean;
		}
		return qPhoneBook.phoneNumber.eq(phoneNumber);
	}

	public BooleanExpression findByOrganization(String organization) {
		if (organization == null) {
			return anyBoolean;
		}
		return qPhoneBook.organization.eq(organization);
	}

	public BooleanExpression findByGithub(String github) {
		if (github == null) {
			return anyBoolean;
		}
		return qPhoneBook.github.eq(github);
	}

	public BooleanExpression getPredicate(SearchCommand searchCommand) throws InvalidSearchParamException {
		if(searchCommand == null) {
			throw new InvalidSearchParamException("invalid search param");
		}
		BooleanExpression be = this.findByEmail(searchCommand.getEmail())
				.and(this.findByGithub(searchCommand.getGithub()))
				.and(this.findByName(searchCommand.getName()))
				.and(this.findByOrganization(searchCommand.getOrganization()))
				.and(this.findByPhoneNumber(searchCommand.getPhoneNumber()));
		return be;
	}


}
