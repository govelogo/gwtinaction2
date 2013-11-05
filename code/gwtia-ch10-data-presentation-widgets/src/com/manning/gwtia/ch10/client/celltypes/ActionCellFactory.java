package com.manning.gwtia.ch10.client.celltypes;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.TextButtonCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellWidget;
import com.google.gwt.user.client.Window;

public class ActionCellFactory {

	static public CellWidget<String> buildTextButtonCell(){
		Cell<String> cell = new TextButtonCell();
		CellWidget<String> cellWidget = new CellWidget<String>(cell);
		cellWidget.setValue("Click Me Text Button!");
		cellWidget.addHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("TextButtonCell Clicked!");
			}}, ClickEvent.getType());
		return cellWidget;
	}

	public static CellWidget<String> buildButtonCell() {
		Cell<String> cell = new ButtonCell();
		CellWidget<String> cellWidget = new CellWidget<String>(cell);
		cellWidget.setValue("Click Me Button!");
		cellWidget.addHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("ButtonCell Clicked!");
			}}, ClickEvent.getType());
		return cellWidget;	
	}

	public static CellWidget<String> buildClickableTextCell() {
		Cell<String> cell = new ClickableTextCell();
		CellWidget<String> cellWidget = new CellWidget<String>(cell);
		cellWidget.setValue("Click Me Text!");
		cellWidget.addHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("ClickableTextCell Clicked!");
			}}, ClickEvent.getType());
		return cellWidget;
	}

	public static CellWidget<String> buildActionCell() {
		Cell<String> cell = new ActionCell<String>("", new Delegate<String>(){
			@Override
			public void execute(String object) {
				Window.alert("Action cell has been Clicked!");						
			}
		});
		CellWidget<String> cellWidget = new CellWidget<String>(cell);
		cellWidget.setValue("Action cell");
		return cellWidget;	
	}
}
