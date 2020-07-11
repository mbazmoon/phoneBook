package com.azmoon.phonebook.api.web.queue;

import com.azmoon.phonebook.client.github.GithubClient;
import com.azmoon.phonebook.exception.PhoneBookNotFoundException;
import com.azmoon.phonebook.model.PhoneBook;
import com.azmoon.phonebook.model.Status;
import com.azmoon.phonebook.response.GithubResponse;
import com.azmoon.phonebook.service.phoneBook.PhoneBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class GithubListener {

	private final PhoneBookService phoneBookService;
	private final GithubClient githubClient;

	@RabbitListener(queues = "${github.queue}")
	public void receiveMessage(@Payload String uid) {
		logger.info("going to github inquiry = {}", uid);
		try {
			PhoneBook phoneBook = phoneBookService.findByUid(uid);

			try {
				List<GithubResponse> repository = this.githubClient.getRepository(phoneBook.getGithub());
				phoneBook.setRepository(getRepoNames(repository));
				phoneBook.setStatus(Status.SUCCESS);
				phoneBookService.save(phoneBook);
			} catch (Exception e) {
				logger.error("github inquiry encountered error. going to retry in the future ", e);
			}
		} catch (PhoneBookNotFoundException e) {
			logger.error("could not find phone book to inquiry {}", uid);

		}
	}

	private List<String> getRepoNames(List<GithubResponse> responses) {
		List<String> repositoryNames = new ArrayList<>();
		if (responses!=null) {
			for (GithubResponse response : responses) {
				repositoryNames.add(response.getName());
			}
		}
		return repositoryNames;
	}
}
