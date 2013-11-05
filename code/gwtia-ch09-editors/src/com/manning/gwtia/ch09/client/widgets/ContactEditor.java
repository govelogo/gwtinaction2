package com.manning.gwtia.ch09.client.widgets;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.validation.ConstraintViolation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LongBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.manning.gwtia.ch09.client.ContactFactory;
import com.manning.gwtia.ch09.client.ContactProxy;
import com.manning.gwtia.ch09.client.PhoneProxy;
import com.manning.gwtia.ch09.client.ContactFactory.ContactRequest;
import com.manning.gwtia.ch09.client.ContactFactory.PhoneRequest;
import com.manning.gwtia.ch09.client.views.PhoneListWidget;

public class ContactEditor extends Composite implements Editor<ContactProxy> {
	Logger log = Logger.getLogger("");

	interface ContactEditorUiBinder extends UiBinder<Widget, ContactEditor> {
	}

	private static ContactEditorUiBinder uiBinder = GWT
			.create(ContactEditorUiBinder.class);

	interface Driver extends
			RequestFactoryEditorDriver<ContactProxy, ContactEditor> {
	}

	private ContactRequest requestContext;
	private ContactFactory requestFactory;
	private ContactProxy contactProxy;

	Driver driver = GWT.create(Driver.class);
	@Ignore
	@UiField
	LongBox fetchId;

	@UiField
	LongBox id;

	@UiField
	TextBox name;
	@UiField
	TextBox email;
	@UiField
	PhoneListWidget phonesWidget;
	@UiField
	TextArea notes;

	@UiField
	Button saveContactButton;

	@UiField
	Button fetchContactButton;

	@UiField
	Button deleteContactButton;

	@UiField
	Button clearContactButton;

	public ContactEditor() {
		initWidget(uiBinder.createAndBindUi(this));
		requestFactory = GWT.create(ContactFactory.class);
		requestContext = requestFactory.createContactRequest();
		final EventBus eventBus = new SimpleEventBus();
		requestFactory.initialize(eventBus);
		driver.initialize(requestFactory, this);
		contactProxy = requestContext.create(ContactProxy.class);
		driver.edit(contactProxy, requestContext);
		deleteContactButton.setEnabled(false);
	}

	@UiHandler("fetchContactButton")
	void onClickFetch(ClickEvent e) {

		if ((fetchId.getValue()==null) || fetchId.getValue().equals("")){
			Window.alert("Select a contact number first");
			return;
		}
		
		final ContactRequest context = requestFactory.createContactRequest();

		requestContext = (ContactRequest) driver.flush();

		requestContext.find(fetchId.getValue()).fire(
				new Receiver<ContactProxy>() {
					@Override
					public void onSuccess(ContactProxy response) {
						if (response != null) {
							contactProxy = context.edit(response);
							driver.edit(contactProxy, context);
							deleteContactButton.setEnabled(true);
							final PhoneRequest request = requestFactory
									.createPhoneRequest();
							
							request.phoneList(contactProxy.getId()).fire(
									new Receiver<List<PhoneProxy>>() {
										public void onSuccess(
												List<PhoneProxy> phones) {

											for (PhoneProxy p : phones) {
												if (contactProxy.getPhones() == null) {
													contactProxy
															.setPhones(new ArrayList<PhoneProxy>());
												}
												contactProxy.getPhones().add(p);
											}
											phonesWidget
													.setListOfPhones(phones);
											deleteContactButton
													.setEnabled(true);

										}
									});

						} else {
							Window.alert("There is no contact with id:"
									+ fetchId.getValue());

						}

					}

					@Override
					public void onConstraintViolation(
							Set<ConstraintViolation<?>> errors) {
						StringBuilder sb = new StringBuilder();
						for (ConstraintViolation<?> e : errors) {
							sb.append(e.getPropertyPath()).append(": ")
									.append(e.getMessage());
						}
						Window.alert(sb.toString());
					}
				});

	}

	@UiHandler("clearContactButton")
	void onClickClearContact(ClickEvent e) {
		reset();
		deleteContactButton.setEnabled(false);
	}

	
	public void reset() {
		requestContext = requestFactory.createContactRequest();
		// New proxy
		contactProxy = requestContext.create(ContactProxy.class);
		driver.edit(contactProxy, requestContext);
		phonesWidget.clearList();
	}

	@UiHandler("saveContactButton")
	void onClickSave(ClickEvent e) {
		requestContext = (ContactRequest) driver.flush();
		final ContactRequest context = requestFactory.createContactRequest();
		if (driver.hasErrors()) {
			Window.alert("Driver errors!");
		}
		// persist in the database
		requestContext.persist(contactProxy).fire(new Receiver<ContactProxy>() {
			@Override
			public void onSuccess(ContactProxy response) {
				// sync edited contact proxy with persisted
				contactProxy = context.edit(response);
				driver.edit(contactProxy, context);
				final PhoneRequest phoneRequest = requestFactory
						.createPhoneRequest();
				for (PhoneProxy phoneProxy : phonesWidget.getListOfPhones()) {
					PhoneProxy newProxy = phoneRequest.create(PhoneProxy.class);
					if (phoneProxy.getContactId() == null) {
						phoneProxy.setContactId(response.getId());
					}
					newProxy.setContactId(phoneProxy.getContactId());
					newProxy.setNumber(phoneProxy.getNumber());
					newProxy.setType(phoneProxy.getType());
					newProxy.setId(phoneProxy.getId());

					phoneRequest.persist().using(newProxy);

				}
				phoneRequest.fire(new Receiver<Void>() {

					@Override
					public void onSuccess(Void arg0) {
						final PhoneRequest request = requestFactory
								.createPhoneRequest();

						request.phoneList(contactProxy.getId()).fire(
								new Receiver<List<PhoneProxy>>() {
									public void onSuccess(
											List<PhoneProxy> phones) {

										for (PhoneProxy p : phones) {
											if (contactProxy.getPhones() == null) {
												contactProxy
														.setPhones(new ArrayList<PhoneProxy>());
											}
											contactProxy.getPhones().add(p);
										}
										phonesWidget.setListOfPhones(phones);
										deleteContactButton.setEnabled(true);

									}
								});
					}

				});

			}

			@Override
			public void onConstraintViolation(Set<ConstraintViolation<?>> errors) {
				StringBuilder sb = new StringBuilder();
				for (ConstraintViolation<?> e : errors) {
					sb.append(e.getPropertyPath()).append(": ")
							.append(e.getMessage());
				}
				Window.alert(sb.toString());
			}
		});
	}

	@UiHandler("deleteContactButton")
	void onClickDelete(ClickEvent e) {
		final ContactRequest context = requestFactory.createContactRequest();

		requestContext = (ContactRequest) driver.flush();
		if (driver.hasErrors()) {
			Window.alert("Driver errors!");
		}

		requestContext.remove(contactProxy).fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				contactProxy = context.create(ContactProxy.class);
				driver.edit(contactProxy, context);
				phonesWidget.clearList();
				deleteContactButton.setEnabled(false);
				Window.alert("Contact Deleted");
			}

		});

	}

}
