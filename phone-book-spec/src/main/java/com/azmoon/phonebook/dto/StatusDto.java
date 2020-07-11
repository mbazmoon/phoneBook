package com.azmoon.phonebook.dto;

public enum StatusDto {
	INITIATED(0), PENDING(1), SUCCESS(2);

	private int value;

	StatusDto(int value) {
		this.value = value;
	}

	public int toValue() {
		return value;
	}
}
