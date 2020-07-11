package com.azmoon.phonebook.model.dao;

import com.azmoon.phonebook.model.PhoneBook;
import com.azmoon.phonebook.model.Status;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneBookDao extends MongoRepository<PhoneBook, String>, QuerydslPredicateExecutor<PhoneBook> {
	Optional<PhoneBook> findByPhoneNumber(String phoneNumber);

	Optional<PhoneBook> findByUid(String uid);

	List<PhoneBook> findByStatus(Status status, PageRequest page);
}
