package com.azmoon.phonebook.service.github;

import com.azmoon.phonebook.model.PhoneBook;
import com.azmoon.phonebook.model.Status;
import com.azmoon.phonebook.service.phoneBook.PhoneBookService;
import com.azmoon.phonebook.util.ConfigProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GithubServiceImpl implements GithubService {

	private final RabbitTemplate rabbitTemplate;
	private final ConfigProvider configProvider;
	private final PhoneBookService phoneBookService;

	@Override
	public void sendToGithubQueue(PhoneBook phoneBook) {
		logger.info("going to queue ={}",phoneBook);
		try {
			rabbitTemplate.convertAndSend(configProvider.getGithubExchange(), configProvider.getGithubRoutingKey(), phoneBook.getUid());
		} catch (Exception e) {
			logger.error("sending phone book to queue encountered an error:", e);
		} finally {
			phoneBook.setStatus(Status.PENDING);
			phoneBookService.save(phoneBook);
		}
	}


	@Scheduled(fixedDelay = 600000)
	public void githubExhaustedPhoneBook() {
		List<PhoneBook> phoneBooks = phoneBookService.getExhaustedPhoneBook(PageRequest.of(0, 100));
		logger.info("exhausted phoneBook number: {}", phoneBooks.size());

		if (!phoneBooks.isEmpty()) {
			for (PhoneBook phoneBook : phoneBooks) {
				sendToGithubQueue(phoneBook);
			}
		}
	}
}
