package com.azmoon.phonebook.api.web.phoneBook;

import com.azmoon.phonebook.exception.ServiceException;
import com.azmoon.phonebook.mapper.PhoneBookMapper;
import com.azmoon.phonebook.model.PhoneBook;
import com.azmoon.phonebook.request.PhoneBookRequest;
import com.azmoon.phonebook.request.SearchRequest;
import com.azmoon.phonebook.response.PhoneBookDetailResponse;
import com.azmoon.phonebook.response.ResponseMessage;
import com.azmoon.phonebook.response.ResponseService;
import com.azmoon.phonebook.service.phoneBook.SearchService;
import com.azmoon.phonebook.service.phoneBook.impl.PhoneBookServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/phone-books")
public class PhoneBookResource {

	private final PhoneBookServiceImpl phoneBookService;
	private final PhoneBookMapper mapper;
	private final SearchService searchService;


	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PhoneBookDetailResponse> addPhoneBook(@RequestBody @Valid PhoneBookRequest request)
			throws ServiceException {
		logger.info("going to add phone book with request;{}", request);
		return ResponseEntity.ok(mapper.toPhoneBookResponse(
				phoneBookService.register(mapper.toPhoneBook(request))));
	}


	@PostMapping(path = "/search", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<? extends ResponseService> search(@RequestBody final SearchRequest searchRequest) throws Exception {
		logger.debug("search api gonna processed with request = {}", searchRequest);
		List<PhoneBook> phoneBooks = searchService.doSearch(mapper.toSearchCommand(searchRequest));
		ResponseService response = mapper.toPhoneBookListResponse(phoneBooks);
		response.setResult(ResponseMessage.SUCCESS);
		return ResponseEntity.ok(response);

	}
}
