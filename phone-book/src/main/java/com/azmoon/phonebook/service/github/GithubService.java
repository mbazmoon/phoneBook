package com.azmoon.phonebook.service.github;


import com.azmoon.phonebook.model.PhoneBook;

public interface GithubService {
	void sendToGithubQueue(PhoneBook phoneBook);
}
