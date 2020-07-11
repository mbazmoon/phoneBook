package com.azmoon.phonebook.service.phoneBook;

import com.azmoon.phonebook.exception.DuplicatePhoneNumberException;
import com.azmoon.phonebook.exception.PhoneBookNotFoundException;
import com.azmoon.phonebook.model.PhoneBook;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface PhoneBookService {
	PhoneBook register(PhoneBook phoneBook) throws DuplicatePhoneNumberException;

	PhoneBook save(PhoneBook phoneBook);

	List<PhoneBook> getExhaustedPhoneBook(PageRequest page);

	PhoneBook findByUid(String uid) throws PhoneBookNotFoundException;
}
