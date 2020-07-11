package com.azmoon.phonebook.model;

public enum Status {
	INITIATED(0), PENDING(1), SUCCESS(2);

	private int value;

	Status(int value) {
		this.value = value;
	}

	public int toValue() {
		return value;
	}
}
