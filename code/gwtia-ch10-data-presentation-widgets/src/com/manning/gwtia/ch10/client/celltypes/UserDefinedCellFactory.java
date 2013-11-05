package com.manning.gwtia.ch10.client.celltypes;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CompositeCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.HasCell;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellWidget;
import com.manning.gwtia.ch10.client.celltypes.custom.PhotoCellWithBuilder;
import com.manning.gwtia.ch10.client.celltypes.custom.PhotoCellWithTemplate;
import com.manning.gwtia.ch10.client.celltypes.custom.PhotoCellWithUiBinder;
import com.manning.gwtia.ch10.client.celltypes.custom.VerticalCompositeCell;
import com.manning.gwtia.ch10.client.cellwidget.common.CalendarFactory;
import com.manning.gwtia.ch10.shared.PhotoDetails;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class UserDefinedCellFactory {

	static private PhotoDetails thePhoto;

	static private PhotoDetails getPhoto() {
		if (thePhoto == null)
			thePhoto = new PhotoDetails();
		Date date;
		try{
			date = CalendarFactory.getClientSideFormatter().parse("15/01/2012");
		} catch (Exception e){
			date = new Date();
		}
		thePhoto.setDate(date);
		thePhoto.setTitle("Wonderful Sunset");
		return thePhoto;
	}

	public static CellWidget<PhotoDetails> buildUserDefinedTemplate() {
		PhotoCellWithTemplate photo = new PhotoCellWithTemplate();
		CellWidget<PhotoDetails> cellWidget = new CellWidget<PhotoDetails>(
				photo);
		getPhoto();
		thePhoto.setLargeUrl("red");
		cellWidget.setValue(thePhoto);
		return cellWidget;
	}

	public static CellWidget<PhotoDetails> buildUserDefinedBuilder() {
		PhotoCellWithBuilder photo = new PhotoCellWithBuilder();
		CellWidget<PhotoDetails> cellWidget = new CellWidget<PhotoDetails>(
				photo);
		getPhoto();
		thePhoto.setLargeUrl("yellow");
		cellWidget.setValue(thePhoto);
		return cellWidget;
	}

	public static CellWidget<PhotoDetails> buildUserDefinedUiBinder() {
		PhotoCellWithUiBinder photo = new PhotoCellWithUiBinder();
		CellWidget<PhotoDetails> cellWidget = new CellWidget<PhotoDetails>(
				photo);
		getPhoto();
		thePhoto.setLargeUrl("orange");
		cellWidget.setValue(thePhoto);
		return cellWidget;
	}	
	
	/**
	 * For use with the composite cell examples
	 * @return
	 */
	private static ArrayList<HasCell<PhotoDetails, ?>> addCells(){
		ArrayList<HasCell<PhotoDetails, ?>> cells = new ArrayList<HasCell<PhotoDetails, ?>>();
		cells.add(new HasCell<PhotoDetails, String>() {

			private TextCell cell = new TextCell();

			@Override
			public Cell<String> getCell() {
				return cell;
			}

			@Override
			public FieldUpdater<PhotoDetails, String> getFieldUpdater() {
				return null;
			}

			@Override
			public String getValue(PhotoDetails object) {
				return (object == null) ? "" : object.getTitle();
			}
		});
		cells.add(new HasCell<PhotoDetails, SafeHtml>() {

			private SafeHtmlCell cell = new SafeHtmlCell();

			@Override
			public Cell<SafeHtml> getCell() {
				return cell;
			}

			@Override
			public FieldUpdater<PhotoDetails, SafeHtml> getFieldUpdater() {
				return null;
			}

			@Override
			public SafeHtml getValue(PhotoDetails object) {
				SafeHtmlBuilder toReturn = new SafeHtmlBuilder();
				if (object!=null){
					toReturn.appendHtmlConstant("<img src=\"\" width = 80px height=60px background-color=\""+object.getLargeUrl()+"\"></img>");
				}
				return toReturn.toSafeHtml();
			}
		});
		cells.add(new HasCell<PhotoDetails, String>() {

			private TextCell cell = new TextCell();

			@Override
			public Cell<String> getCell() {
				return cell;
			}

			@Override
			public FieldUpdater<PhotoDetails, String> getFieldUpdater() {
				return null;
			}

			@Override
			public String getValue(PhotoDetails object) {
				return (object == null) ? "" : CalendarFactory.getDisplayFormatter().format(object.getDate());
			}
		});
		return cells;
	}
		
	public static CellWidget<PhotoDetails> buildComposite() {
		ArrayList<HasCell<PhotoDetails, ?>> cells = addCells();
		CompositeCell<PhotoDetails> compCell = new CompositeCell<PhotoDetails>(
				cells);
		getPhoto();
		thePhoto.setLargeUrl("yellow");
		CellWidget<PhotoDetails> cellWidget = new CellWidget<PhotoDetails>(
				compCell);
		cellWidget.setValue(thePhoto);
		return cellWidget;
	}
	
	public static CellWidget<PhotoDetails> buildVerticalComposite() {
		ArrayList<HasCell<PhotoDetails, ?>> cells = addCells();
		CompositeCell<PhotoDetails> compCell = new VerticalCompositeCell(cells);
		getPhoto();
		thePhoto.setLargeUrl("orange");
		CellWidget<PhotoDetails> cellWidget = new CellWidget<PhotoDetails>(
				compCell);
		cellWidget.setValue(thePhoto);
		return cellWidget;
	}
	
}
