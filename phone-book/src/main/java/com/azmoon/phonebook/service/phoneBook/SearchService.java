package com.azmoon.phonebook.service.phoneBook;

import com.azmoon.phonebook.exception.InvalidSearchParamException;
import com.azmoon.phonebook.model.PhoneBook;
import com.azmoon.phonebook.model.SearchCommand;

import java.util.List;

public interface SearchService {
	List<PhoneBook> doSearch(SearchCommand searchCommand) throws InvalidSearchParamException;
}
