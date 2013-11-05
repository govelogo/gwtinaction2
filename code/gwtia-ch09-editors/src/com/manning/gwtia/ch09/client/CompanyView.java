package com.manning.gwtia.ch09.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch09.client.intro.IntroPanel;
import com.manning.gwtia.ch09.client.views.EmployeeView;
import com.manning.gwtia.ch09.client.views.ListAllContactsEditor;
import com.manning.gwtia.ch09.client.views.ListAllContactsView;
import com.manning.gwtia.ch09.client.widgets.ContactEditor;
import com.manning.gwtia.ch09.client.widgets.PhoneBookView;

public class CompanyView extends Composite {
	interface Binder extends UiBinder<Widget, CompanyView> {
	}

	interface Resources extends ClientBundle {
		@Source("gwtia.jpg")
		public ImageResource logo();
	}

	private static Binder uiBinder = GWT.create(Binder.class);

	private IntroPanel introPanel = null;

	private EmployeeView employeeView = null;

	private PhoneBookView phoneBookView = null;

	private ContactEditor contactEditor = null;

	private ListAllContactsView allContactsEditor = null;

	private ListAllContactsEditor allContactsEditor2 = null;

	@UiField
	Button employeesButton;

	@UiField
	Button phoneBookButton;

	@UiField
	Button contactButton;

	@UiField
	FlowPanel examplesPanel;

	@UiField
	Button introPanelButton;

	@UiField
	Button listAllContactsButton;

	@UiField
	Button listAllContactsButton2;

	public CompanyView() {
		initWidget(uiBinder.createAndBindUi(this));
		setWidgetToMaxWidthAndHeight();
		introPanel = new IntroPanel();
		setWidgetAsExample(introPanel);
	}

	private void setWidgetAsExample(Widget widget) {
		examplesPanel.add(widget);
		setWidgetToMaxWidthAndHeight();
	}

	private void hideAllWidgets() {
		if (introPanel != null) {
			introPanel.setVisible(false);
		}
		if (employeeView != null) {
			employeeView.setVisible(false);
		}
		if (phoneBookView != null) {
			phoneBookView.setVisible(false);
		}

		if (contactEditor != null) {
			contactEditor.setVisible(false);
		}
		if (allContactsEditor != null) {
			allContactsEditor.setVisible(false);
		}

		if (allContactsEditor2 != null) {
			allContactsEditor2.setVisible(false);
		}

	}

	@UiHandler("introPanelButton")
	void onClickIntroduction(ClickEvent e) {
		History.newItem("");
	}

	public void showIntro() {
		hideAllWidgets();
		if (introPanel == null) {
			introPanel = new IntroPanel();
			setWidgetAsExample(introPanel);
		} else {
			introPanel.setVisible(true);
		}
	}

	@UiHandler("employeesButton")
	void onClickEmployees(ClickEvent e) {
		History.newItem(HistoryTokens.EMPLOYEES);
	}

	public void showEmployees() {
		hideAllWidgets();
		if (employeeView == null) {
			employeeView = new EmployeeView();
			setWidgetAsExample(employeeView);
		} else {
			employeeView.setVisible(true);
		}
	}

	@UiHandler("phoneBookButton")
	void onClickPhoneBook(ClickEvent e) {
		History.newItem(HistoryTokens.PHONEBOOK);
	}

	public void showPhoneBook() {
		hideAllWidgets();
		if (phoneBookView == null) {
			phoneBookView = new PhoneBookView();
			setWidgetAsExample(phoneBookView);
		} else {
			phoneBookView.setVisible(true);
		}
	}

	@UiHandler("contactButton")
	void onClickContacts(ClickEvent e) {
		History.newItem(HistoryTokens.CONTACTS);
	}

	public void showContacts() {
		hideAllWidgets();
		if (contactEditor == null) {
			contactEditor = new ContactEditor();
			setWidgetAsExample(contactEditor);
		} else {
			contactEditor.reset();
			contactEditor.setVisible(true);
		}
	}

	@UiHandler("listAllContactsButton")
	void onClickListAllContacts(ClickEvent e) {
		History.newItem(HistoryTokens.LIST_1);
	}

	public void showList1() {
		hideAllWidgets();
		if (allContactsEditor == null) {
			allContactsEditor = new ListAllContactsView();
			allContactsEditor.findAllContacts();
			setWidgetAsExample(allContactsEditor);
		} else {
			allContactsEditor.findAllContacts();
			allContactsEditor.setVisible(true);
		}
	}

	@UiHandler("listAllContactsButton2")
	void onClickListAllContacts2(ClickEvent e) {
		History.newItem(HistoryTokens.LIST_2);
	}

	public void showList2() {
		hideAllWidgets();
		if (allContactsEditor2 == null) {
			allContactsEditor2 = new ListAllContactsEditor();
			allContactsEditor2.findAllContacts();
			setWidgetAsExample(allContactsEditor2);
		} else {
			allContactsEditor2.findAllContacts();
			allContactsEditor2.setVisible(true);
		}
	}

	private void setWidgetToMaxWidthAndHeight() {
		setWidth("100%");
		setHeight(Window.getClientHeight() + "px");
	}
}
