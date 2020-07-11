package com.azmoon.phonebook.service.phoneBook.impl;

import com.azmoon.phonebook.exception.InvalidSearchParamException;
import com.azmoon.phonebook.model.PhoneBook;
import com.azmoon.phonebook.model.PredicatePhoneBook;
import com.azmoon.phonebook.model.SearchCommand;
import com.azmoon.phonebook.model.dao.PhoneBookDao;
import com.azmoon.phonebook.service.phoneBook.SearchService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

	private final PhoneBookDao phoneBookDao;
	private final PredicatePhoneBook predicatePhoneBook;

	@Override
	public List<PhoneBook> doSearch(SearchCommand searchCommand) throws InvalidSearchParamException {

		Predicate predicate = predicatePhoneBook.getPredicate(searchCommand);
		return (List<PhoneBook>) phoneBookDao.findAll(predicate);
	}
}
