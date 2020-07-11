package com.azmoon.phonebook.service.PhoneBook;

import com.azmoon.phonebook.model.PhoneBook;
import com.azmoon.phonebook.model.PredicatePhoneBook;
import com.azmoon.phonebook.model.SearchCommand;
import com.azmoon.phonebook.model.dao.PhoneBookDao;
import com.azmoon.phonebook.service.phoneBook.SearchService;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhoneBookServiceTest {

	@Autowired
	private SearchService searchService;

	@MockBean
	private PhoneBookDao phoneBookDao;

	@MockBean
	private PredicatePhoneBook predicatePhoneBook;

	@MockBean
	private BooleanExpression booleanExpression;


	@Test
	public void testSearchEmployee() throws Exception {

		SearchCommand search = new SearchCommand();
		search.setName("mohammad");
		PhoneBook phoneBook = this.loadPhoneBookForTest();
		List<PhoneBook> phoneBooks = new ArrayList<>();
		phoneBooks.add(phoneBook);

		when(this.predicatePhoneBook.getPredicate(any(SearchCommand.class))).thenReturn(this.booleanExpression);
		when(this.phoneBookDao.findAll(any(BooleanExpression.class))).thenReturn(phoneBooks);

		List<PhoneBook> searchResult = this.searchService.doSearch(search);

		assertNotNull(searchResult);
		assertEquals(Long.valueOf(searchResult.size()), Long.valueOf(1));
	}

	private PhoneBook loadPhoneBookForTest() {
		PhoneBook phoneBook = new PhoneBook();
		phoneBook.setName("mohammad");
		phoneBook.setPhoneNumber("0217777777");
		phoneBook.setGithub("mbazmoon");
		phoneBook.setEmail("m.b.azmoon@gmail.com");
		phoneBook.setOrganization("snapp");
		return phoneBook;
	}

}
