package com.azmoon.phonebook.api.web.phoneBook;

import com.azmoon.phonebook.AbstractBaseIT;
import com.azmoon.phonebook.model.PhoneBook;
import com.azmoon.phonebook.model.dao.PhoneBookDao;
import com.azmoon.phonebook.request.PhoneBookRequest;
import com.azmoon.phonebook.response.PhoneBookDetailResponse;
import com.azmoon.phonebook.response.ResponseMessage;
import com.azmoon.phonebook.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

public class PhoneBookResourceIT extends AbstractBaseIT {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private PhoneBookDao phoneBookDao;

	@Test
	public void registerNewPhoneBook() {
		Mockito.when(phoneBookDao.save(any())).thenReturn(newPhoneBook("1", "02177777772", "mohammad", "mbazmoon"));
		String uri = "http://localhost:" + port + "/azmoon/phone-books/";
		HttpEntity<?> httpEntity = new HttpEntity<>(new PhoneBookRequest("02177777772", "mbazmoon", "mohammad", "m.b.azmoon@gmail.com", "snapp"),
				TestUtils.createHttpHeader());
		ResponseEntity<PhoneBookDetailResponse> response = restTemplate.exchange(uri, HttpMethod.POST, httpEntity,
				PhoneBookDetailResponse.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response).isNotNull();
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getResult()).isNotNull();
		assertThat(response.getBody().getResult().getTitle()).isEqualTo(ResponseMessage.SUCCESS);
	}

	@Test
	public void registerNewPhoneBook_Duplicated_PhoneNumber() {
		Mockito.when(phoneBookDao.findByPhoneNumber(any())).thenReturn(Optional.of(newPhoneBook("1", "02177777772", "mohammad", "mbazmoon")));
		String uri = "http://localhost:" + port + "/azmoon/phone-books/";
		HttpEntity<?> httpEntity = new HttpEntity<>(new PhoneBookRequest("02177777772", "mbazmoon", "mohammad", "m.b.azmoon@gmail.com", "snapp"),
				TestUtils.createHttpHeader());
		ResponseEntity<PhoneBookDetailResponse> response = restTemplate.exchange(uri, HttpMethod.POST, httpEntity,
				PhoneBookDetailResponse.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
		assertThat(response).isNotNull();
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getResult()).isNotNull();
		assertThat(response.getBody().getResult().getTitle()).isEqualTo(ResponseMessage.DUPLICATE);
	}


	private PhoneBook newPhoneBook(String uid, String phoneNumber, String name, String repoName) {
		PhoneBook phoneBook = new PhoneBook();
		phoneBook.setUid(uid);
		phoneBook.setName(name);
		phoneBook.setGithub(repoName);
		phoneBook.setPhoneNumber(phoneNumber);
		return phoneBook;
	}

}
