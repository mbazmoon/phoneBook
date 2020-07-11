package com.azmoon.phonebook.mapper;

import com.azmoon.phonebook.dto.PhoneBookDto;
import com.azmoon.phonebook.model.PhoneBook;
import com.azmoon.phonebook.model.SearchCommand;
import com.azmoon.phonebook.request.PhoneBookRequest;
import com.azmoon.phonebook.request.SearchRequest;
import com.azmoon.phonebook.response.PhoneBookDetailResponse;
import com.azmoon.phonebook.response.PhoneBookListResponse;
import com.azmoon.phonebook.response.ResponseMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", imports = {ResponseMessage.class})
public interface PhoneBookMapper {

	PhoneBook toPhoneBook(PhoneBookRequest request);

	SearchCommand toSearchCommand(SearchRequest request);

	@Mapping(target = "result", expression = "java(ResponseMessage.SUCCESS)")
	PhoneBookDetailResponse toPhoneBookResponse(PhoneBook phoneBook);

	default PhoneBookListResponse toPhoneBookListResponse(List<PhoneBook> phoneBooks) {
		PhoneBookListResponse response = new PhoneBookListResponse();
		response.setPhoneBooks(toPhoneBookDto(phoneBooks));
		response.setResult(ResponseMessage.SUCCESS);
		return response;
	}

	List<PhoneBookDto> toPhoneBookDto(List<PhoneBook> phoneBooks);
}
