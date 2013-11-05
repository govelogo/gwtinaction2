package com.manning.gwtia.ch10.client.celltypes;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch10.shared.PhotoDetails;

public class CellTypesExample extends Composite {

	interface CellTypesUiBinder extends UiBinder<Widget, CellTypesExample> {
	}

	private static CellTypesUiBinder uiBinder = GWT
			.create(CellTypesUiBinder.class);

	// Display Cells
	@UiField(provided = true)
	CellWidget<String> textCell;
	@UiField(provided = true)
	CellWidget<Number> numberCell;
	@UiField(provided = true)
	CellWidget<Date> dateCell;
	@UiField(provided = true)
	CellWidget<String> imageCell;
	@UiField(provided = true)
	CellWidget<String> imageResourceCell;
	@UiField(provided = true)
	CellWidget<String> imageLoadingCell;
	@UiField(provided = true)
	CellWidget<SafeUri> safeImageCell;
	@UiField(provided = true)
	CellWidget<SafeHtml> safeHtmlCell;
	@UiField(provided = true)
	CellWidget<String> iconCellDecorator;

	// Editable Cells
	@UiField(provided = true)
	CellWidget<String> editableTextCell;
	@UiField(provided = true)
	CellWidget<String> inputCell;
	@UiField(provided = true)
	CellWidget<Boolean> checkboxCell;
	@UiField(provided = true)
	CellWidget<String> selectionCell;
	@UiField(provided = true)
	CellWidget<Date> datePickerCell;

	// Action Cells
	@UiField(provided = true)
	CellWidget<String> buttonCell;
	@UiField(provided = true)
	CellWidget<String> buttonTextCell;
	@UiField(provided = true)
	CellWidget<String> clickableTextCell;
	@UiField(provided = true)
	CellWidget<String> actionCell;


	// Composite Cell
	@UiField(provided = true)
	CellWidget<PhotoDetails> compositeCell;
	@UiField(provided = true)
	CellWidget<PhotoDetails> compositeCellUser;

	
	// User Defined Cells;
	@UiField(provided = true)
	CellWidget<PhotoDetails> userDefinedHtmlTemplate;
	@UiField(provided = true)
	CellWidget<PhotoDetails> userDefinedHtmlBuilder;
	@UiField(provided = true)
	CellWidget<PhotoDetails> userDefinedUiBinder;
	
	public CellTypesExample() {
		// Build Display Cells
		textCell = DisplayCellFactory.buildTextCell();
		numberCell = DisplayCellFactory.buildNumberCell();
		dateCell = DisplayCellFactory.buildDateCell();
		imageCell = DisplayCellFactory.buildImageCell();
		imageResourceCell = DisplayCellFactory.buildTextCell();
		imageLoadingCell = DisplayCellFactory.buildImageLoadingCell();
		safeImageCell = DisplayCellFactory.buildSafeImageCell();
		safeHtmlCell = DisplayCellFactory.buildSafeHtmlCell();
		iconCellDecorator = DisplayCellFactory.buildIconCellDecorator();

		// Build Edit Cells
		editableTextCell = EditCellFactory.buildEditTextCell();
		inputCell = EditCellFactory.buildTextInputCell();
		checkboxCell = EditCellFactory.buildCheckboxCell();
		selectionCell = EditCellFactory.buildSelectionCell();
		datePickerCell = EditCellFactory.buildDatePickerCell();

		// Build Action Cells
		buttonCell = ActionCellFactory.buildButtonCell();
		clickableTextCell = ActionCellFactory.buildClickableTextCell();
		buttonTextCell = ActionCellFactory.buildTextButtonCell();
		actionCell = ActionCellFactory.buildActionCell();

		// Build Composite Cell
		compositeCell = UserDefinedCellFactory.buildComposite();
		compositeCellUser = UserDefinedCellFactory.buildVerticalComposite();

		// Build User Defined Cells
		userDefinedHtmlBuilder = UserDefinedCellFactory.buildUserDefinedBuilder();
		userDefinedHtmlTemplate = UserDefinedCellFactory.buildUserDefinedTemplate();
		userDefinedUiBinder = UserDefinedCellFactory.buildUserDefinedUiBinder();

		initWidget(uiBinder.createAndBindUi(this));
		this.setSize("100%", "100%");
	}
}
