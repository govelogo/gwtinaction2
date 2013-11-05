package com.manning.gwtia.ch09.client.widgets;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch09.client.domain.PhoneNumber;

public class PhoneNumberEditor extends Composite implements Editor<PhoneNumber> {

	Logger log = Logger.getLogger("");

	private static PhoneNumberEditorUiBinder uiBinder = GWT
			.create(PhoneNumberEditorUiBinder.class);

	interface PhoneNumberEditorUiBinder extends
			UiBinder<Widget, PhoneNumberEditor> {
	}

	@UiField
	TextBox type;
	@UiField
	TextBox number;

	public PhoneNumberEditor() {
		initWidget(uiBinder.createAndBindUi(this));

	}

	public void resetValues() {
		type.setValue("");
		number.setValue("");

	}

}
