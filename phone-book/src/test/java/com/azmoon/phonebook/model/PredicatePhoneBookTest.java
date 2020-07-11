package com.azmoon.phonebook.model;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PredicatePhoneBookTest {
	@Autowired
	private PredicatePhoneBook predicatePhoneBook;

	@Test
	public void testFindPhoneBookByName() {
		String employeeId ="mohammad";
		BooleanExpression booleanExpression = this.predicatePhoneBook.findByName(employeeId);
		assertNotNull(booleanExpression);
	}

	@Test
	public void testFindPhoneBookByOrganization() {
		String organization ="snapp";
		BooleanExpression booleanExpression = this.predicatePhoneBook.findByName(organization);
		assertNotNull(booleanExpression);
	}

	@Test
	public void testFindPhoneBookByGithub() {
		String github ="mbazmoon";
		BooleanExpression booleanExpression = this.predicatePhoneBook.findByName(github);
		assertNotNull(booleanExpression);
	}

	@Test
	public void testFindPhoneBookByPhoneNumber() {
		String phoneNumber ="02177777777";
		BooleanExpression booleanExpression = this.predicatePhoneBook.findByName(phoneNumber);
		assertNotNull(booleanExpression);
	}

	@Test
	public void testFindPhoneBookByEmail() {
		String email ="m.b.azmoon@gmail.com";
		BooleanExpression booleanExpression = this.predicatePhoneBook.findByName(email);
		assertNotNull(booleanExpression);
	}
}
