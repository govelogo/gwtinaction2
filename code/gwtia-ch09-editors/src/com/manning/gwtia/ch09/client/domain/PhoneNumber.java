package com.manning.gwtia.ch09.client.domain;

public class PhoneNumber {

	private String type;
	private String number;

	public PhoneNumber() {
		setType("Mobile");
		setNumber("08097654321");
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
