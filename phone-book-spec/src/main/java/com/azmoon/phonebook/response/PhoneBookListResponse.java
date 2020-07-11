package com.azmoon.phonebook.response;

import com.azmoon.phonebook.dto.PhoneBookDto;
import lombok.Data;

import java.util.List;

@Data
public class PhoneBookListResponse extends ResponseService {
	private List<PhoneBookDto> phoneBooks;
}
