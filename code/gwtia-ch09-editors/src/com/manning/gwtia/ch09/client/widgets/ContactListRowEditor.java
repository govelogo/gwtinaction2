package com.manning.gwtia.ch09.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LongBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch09.client.ContactProxy;

public class ContactListRowEditor extends Composite implements
		LeafValueEditor<ContactProxy> {

	private static ContactListRowEditorUiBinder uiBinder = GWT
			.create(ContactListRowEditorUiBinder.class);

	interface ContactListRowEditorUiBinder extends
			UiBinder<Widget, ContactListRowEditor> {
	}

	@UiField
	LongBox id;

	@UiField
	TextBox name;

	private ContactProxy contactProxy;

	public ContactListRowEditor() {
		initWidget(uiBinder.createAndBindUi(this));

	}

	@Override
	public ContactProxy getValue() {
		return contactProxy;
	}

	@Override
	public void setValue(ContactProxy proxy) {
		id.setValue(proxy.getId().longValue());
		name.setValue(proxy.getName());

		contactProxy = proxy;

	}

}
