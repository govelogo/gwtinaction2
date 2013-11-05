package com.manning.gwtia.ch09.client.views;

/*
*
*  List of contacts using Adapter
* 
* 
*/


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.adapters.HasDataEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.manning.gwtia.ch09.client.ContactFactory;
import com.manning.gwtia.ch09.client.ContactProxy;
import com.manning.gwtia.ch09.client.ContactFactory.ContactRequest;

public class ListAllContactsView extends Composite {
	Logger log = Logger.getLogger("");

	interface ListAllContactEditorUiBinder extends
			UiBinder<Widget, ListAllContactsView> {
	}

	private static ListAllContactEditorUiBinder uiBinder = GWT
			.create(ListAllContactEditorUiBinder.class);

	private ContactRequest requestContext;
	private ContactFactory requestFactory;
	Driver driver = GWT.create(Driver.class);

	HasDataEditor<ContactProxy> hasDataEditor;

	interface Driver extends RequestFactoryEditorDriver<List<ContactProxy>, //
			HasDataEditor<ContactProxy>> {
	}

	@UiField
	FlowPanel flowPanel;

	private List<ContactProxy> contactProxies;

	@UiField
	Button deleteAllContactsButton;

	public ListAllContactsView() {
		initWidget(uiBinder.createAndBindUi(this));
		deleteAllContactsButton.setEnabled(false);

	}

	public void findAllContacts() {
		flowPanel.clear();
		requestFactory = GWT.create(ContactFactory.class);
		requestContext = requestFactory.createContactRequest();

		// initialize table
		CellTable<ContactProxy> table = new CellTable<ContactProxy>();
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		final EventBus eventBus = new SimpleEventBus();

		requestFactory.initialize(eventBus);
		TextColumn<ContactProxy> idColumn = new TextColumn<ContactProxy>() {

			@Override
			public String getValue(ContactProxy contact) {
				return contact.getId().toString();
			}
		};

		table.addColumn(idColumn, "Id");
		TextColumn<ContactProxy> nameColumn = new TextColumn<ContactProxy>() {
			@Override
			public String getValue(ContactProxy contact) {
				return contact.getName();
			}

		};

		table.setRowCount(5);
		table.setVisibleRange(0, 5);
		table.setColumnWidth(idColumn, "50px");
		table.setColumnWidth(nameColumn, "50px");
		table.setHeight("150px");
		table.setWidth("100%");

		hasDataEditor = HasDataEditor.of(table);
		table.addColumn(nameColumn, "Name");

		contactProxies = new ArrayList<ContactProxy>();
		hasDataEditor.setValue(contactProxies);
		flowPanel.add(table);
		driver.initialize(eventBus, requestFactory, hasDataEditor);

		driver.edit(contactProxies, requestContext);

		requestContext.findAllContacts().fire(
				new Receiver<List<ContactProxy>>() {
					public void onSuccess(List<ContactProxy> contacts) {
						if (contacts.size() == 0) {
							Window.alert("There are no contacts to list!\nTry adding some through the contacts view");
						} else {
							for (ContactProxy c : contacts) {
								log.info("Contact: " + c.getId() + "="
										+ c.getEmail());
							}
							deleteAllContactsButton.setEnabled(true);

						}
						requestContext = requestFactory.createContactRequest();
						driver.edit(contacts, requestContext);
						hasDataEditor.setValue(contacts);
					}
				});
	}

	@UiHandler("deleteAllContactsButton")
	void onClickDeleteAllContacts(ClickEvent e) {
		ContactRequest context = requestFactory.createContactRequest();
		ContactProxy contact = context.create(ContactProxy.class);

		requestContext = (ContactRequest) driver.flush();

		if (driver.hasErrors()) {
			Window.alert("Driver errors!");
		}
		context.remove(contact).fire();

		Receiver<List<ContactProxy>> receiver = new Receiver<List<ContactProxy>>() {
			public void onSuccess(List<ContactProxy> contacts) {
				ContactRequest context = requestFactory.createContactRequest();
				for (ContactProxy contact : contacts) {
					context.remove(context.edit(contact));
				}
				context.fire();
				driver.edit(contacts, requestContext);
				flowPanel.clear();
				deleteAllContactsButton.setEnabled(false);
				Window.alert("All contacts deleted");
			}
		};

		requestFactory.createContactRequest().findAllContacts().to(receiver)
				.fire();
	}

}
