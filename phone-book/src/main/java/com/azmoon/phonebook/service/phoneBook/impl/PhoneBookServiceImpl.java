package com.azmoon.phonebook.service.phoneBook.impl;

import com.azmoon.phonebook.aspect.annotation.Github;
import com.azmoon.phonebook.exception.DuplicatePhoneNumberException;
import com.azmoon.phonebook.exception.PhoneBookNotFoundException;
import com.azmoon.phonebook.model.PhoneBook;
import com.azmoon.phonebook.model.Status;
import com.azmoon.phonebook.model.dao.PhoneBookDao;
import com.azmoon.phonebook.service.phoneBook.PhoneBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PhoneBookServiceImpl implements PhoneBookService {

	private final PhoneBookDao phoneBookDao;

	@Override
	@Github
	public PhoneBook register(PhoneBook phoneBook) throws DuplicatePhoneNumberException {
		if (phoneBookDao.findByPhoneNumber(phoneBook.getPhoneNumber()).isPresent()) {
			logger.error("phone book with phone number={} already exists", phoneBook.getPhoneNumber());
			throw new DuplicatePhoneNumberException("phone number already exists");
		}
		phoneBook.setUid(String.valueOf(Math.abs(UUID.randomUUID().toString().hashCode())));
		phoneBook.setStatus(phoneBook.getGithub() == null ? Status.SUCCESS : Status.PENDING);
		return save(phoneBook);
	}

	@Override
	public PhoneBook save(PhoneBook phoneBook) {
		return phoneBookDao.save(phoneBook);
	}

	@Override
	public List<PhoneBook> getExhaustedPhoneBook(PageRequest page) {
		return phoneBookDao.findByStatus(Status.PENDING, page);
	}

	@Override
	public PhoneBook findByUid(String uid) throws PhoneBookNotFoundException {
		return phoneBookDao.findByUid(uid).orElseThrow(() -> new PhoneBookNotFoundException("there is no phone book with uid code " + uid));
	}
}
