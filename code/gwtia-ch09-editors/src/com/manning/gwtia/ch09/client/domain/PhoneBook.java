package com.manning.gwtia.ch09.client.domain;

public class PhoneBook {
	private Employee employee;
	private PhoneNumber phoneNumber;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhone(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
