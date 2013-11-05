package com.manning.gwtia.ch09.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch09.client.domain.Employee;
import com.manning.gwtia.ch09.client.domain.PhoneBook;
import com.manning.gwtia.ch09.client.domain.PhoneNumber;

/**
 * 
 * @author Tškke
 * 
 */
public class PhoneBookView extends Composite implements PhoneBookEditor {
	private static PhoneBookViewUiBinder uiBinder = GWT
			.create(PhoneBookViewUiBinder.class);

	interface PhoneBookViewUiBinder extends UiBinder<Widget, PhoneBookView> {
	}

	PhoneBook phoneBook;

	@UiField
	Button resetEmployeeButton;

	@UiField
	Button saveEmployeeButton;

	@UiField
	Button fetchEmployeeButton;

	interface Driver extends SimpleBeanEditorDriver<PhoneBook, PhoneBookEditor> {
	}

	Driver driver = GWT.create(Driver.class);

	@UiField
	EmployeeEditor employeeEditor;

	@UiField
	PhoneNumberEditor phoneNumberEditor;

	public PhoneBookView() {
		phoneBook = new PhoneBook();
		initWidget(uiBinder.createAndBindUi(this));
		phoneBook.setEmployee(new Employee());
		phoneBook.setPhone(new PhoneNumber());
		driver.initialize(this);
		driver.edit(phoneBook);
	}

	@Override
	public EmployeeEditor employee() {
		// TODO Auto-generated method stub
		return employeeEditor;
	}

	@Override
	public PhoneNumberEditor phoneNumber() {
		// TODO Auto-generated method stub
		return phoneNumberEditor;
	}

	@UiHandler("resetEmployeeButton")
	void onClickReset(ClickEvent e) {
		this.employeeEditor.resetValues();
		this.phoneNumberEditor.resetValues();
	}

	@UiHandler("saveEmployeeButton")
	void onClickSave(ClickEvent e) {
		driver.flush();
		if (driver.hasErrors()) {
			Window.alert("There are errors!");

		}
	}

	@UiHandler("fetchEmployeeButton")
	void onClickGet(ClickEvent e) {
		driver.edit(phoneBook);

	}

}
