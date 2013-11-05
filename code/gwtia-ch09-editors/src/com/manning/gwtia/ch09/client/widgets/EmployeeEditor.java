package com.manning.gwtia.ch09.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch09.client.domain.Employee;

public class EmployeeEditor extends Composite implements Editor<Employee> {

	private static EmployeeEditorUiBinder uiBinder = GWT
			.create(EmployeeEditorUiBinder.class);

	interface EmployeeEditorUiBinder extends UiBinder<Widget, EmployeeEditor> {
	}

	@UiField
	@Path(value = "name")
	TextBox name;

	@UiField
	@Path(value = "title")
	TextBox employeeTitle;

	@Ignore
	Label id;

	public EmployeeEditor() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void resetValues() {
		name.setValue("");
		employeeTitle.setValue("");

	}

}
