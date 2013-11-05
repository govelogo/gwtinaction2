package com.manning.gwtia.ch10.client.celltypes;

import java.util.Arrays;
import java.util.Date;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.DatePickerCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.cellview.client.CellWidget;
import com.google.gwt.user.client.Window;

public class EditCellFactory {

	static	public CellWidget<String> buildEditTextCell(){
		Cell<String> cell = new EditTextCell();
		CellWidget<String> cellWidget = new CellWidget<String>(cell);
		cellWidget.setValue("Some Editable Text - click me to edit!");
		cellWidget.addValueChangeHandler(new ValueChangeHandler<String>(){
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				Window.alert("Changed EditTextCell value to: "+event.getValue());
			}
		});
		return cellWidget;
	}
	
	static	public CellWidget<String> buildTextInputCell(){
		Cell<String> cell = new TextInputCell();
		CellWidget<String> cellWidget = new CellWidget<String>(cell);
		cellWidget.setValue("Input some text");
		cellWidget.addValueChangeHandler(new ValueChangeHandler<String>(){
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				Window.alert("Changed TextInputCell value to: "+event.getValue());
			}
		});
		return cellWidget;
	}
	
	static	public CellWidget<Boolean> buildCheckboxCell(){
		Cell<Boolean> cell = new CheckboxCell();
		CellWidget<Boolean> cellWidget = new CellWidget<Boolean>(cell);
		cellWidget.setValue(false);
		cellWidget.addValueChangeHandler(new ValueChangeHandler<Boolean>(){
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				Window.alert("Changed CheckboxCell value to: "+event.getValue());
			}
		});
		return cellWidget;
	}
	
	static	public CellWidget<String> buildSelectionCell(){
		String[] ops = {"dog","cat", "mouse", "elephant"};
		Cell<String> cell = new SelectionCell(Arrays.asList(ops));
		CellWidget<String> cellWidget = new CellWidget<String>(cell);
		cellWidget.addValueChangeHandler(new ValueChangeHandler<String>(){
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				Window.alert("SelectionCell selected: "+event.getValue());
			}
		});
		return cellWidget;
	}
	
	
	static	public CellWidget<Date> buildDatePickerCell(){
		DateTimeFormat format = DateTimeFormat.getFormat(PredefinedFormat.DATE_FULL);
		Cell<Date> cell = new DatePickerCell(format);
		CellWidget<Date> cellWidget = new CellWidget<Date>(cell);
		cellWidget.setValue(new Date());
		cellWidget.addValueChangeHandler(new ValueChangeHandler<Date>(){
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				Window.alert("SelectionCell selected: "+event.getValue());
			}
		});
		return cellWidget;
	}
}
