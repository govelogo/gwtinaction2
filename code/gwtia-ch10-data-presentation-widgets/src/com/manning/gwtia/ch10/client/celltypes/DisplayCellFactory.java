package com.manning.gwtia.ch10.client.celltypes;

import java.util.Date;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.IconCellDecorator;
import com.google.gwt.cell.client.ImageCell;
import com.google.gwt.cell.client.ImageLoadingCell;
import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.cell.client.SafeImageCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.cellview.client.CellWidget;

public class DisplayCellFactory {

	static public CellWidget<String> buildTextCell(){
		Cell<String> cell = new TextCell();
		CellWidget<String> cellWidget = new CellWidget<String>(cell);
		cellWidget.setValue("Some Static Text");
		return cellWidget;
	}
	
	static public CellWidget<Number> buildNumberCell(){
		Cell<Number> cell = new NumberCell();
		//Try the following instead of above to see numbers as percentages
		//Cell<Number> cell = new NumberCell(NumberFormat.getPercentFormat());
		CellWidget<Number> cellWidget = new CellWidget<Number>(cell);
		cellWidget.setValue(3.141);
		return cellWidget;
	}
	
	static public CellWidget<Date> buildDateCell(){
		Cell<Date> cell = new DateCell();
		//Try the following instead of above to see a different format for dates
		// Cell<Date> cell = new DateCell(DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT));
		CellWidget<Date> cellWidget = new CellWidget<Date>(cell);
		Date date = new Date();
		cellWidget.setValue(date);
		return cellWidget;
	}
	
	final static String IMAGE_URL = "http://www.manning.com/tacy/tacy_cover150.jpg";
	
	static public CellWidget<String> buildImageCell(){
		Cell<String> cell = new ImageCell();
		CellWidget<String> cellWidget = new CellWidget<String>(cell);
		cellWidget.setValue(IMAGE_URL);
		return cellWidget;
	}
	
	static public CellWidget<String> buildImageLoadingCell(){
		Cell<String> cell = new ImageLoadingCell();
		final CellWidget<String> cellWidget = new CellWidget<String>(cell);		
		cellWidget.setValue(IMAGE_URL);				
		return cellWidget;
	}
	
	static public CellWidget<ImageResource> buildImageResourceCell(){
		Cell<ImageResource> cell = new ImageResourceCell();
		final CellWidget<ImageResource> cellWidget = new CellWidget<ImageResource>(cell);		
		//cellWidget.setValue(IMAGE_URL);				
		return cellWidget;
	}
	
	static public CellWidget<SafeUri> buildSafeImageCell(){
		Cell<SafeUri> cell = new SafeImageCell();
		final CellWidget<SafeUri> cellWidget = new CellWidget<SafeUri>(cell);		
		SafeUri safe = UriUtils.fromString(IMAGE_URL);
		cellWidget.setValue(safe);				
		return cellWidget;
	}
	
	static public CellWidget<SafeHtml> buildSafeHtmlCell(){
		Cell<SafeHtml> cell = new SafeHtmlCell();
		final CellWidget<SafeHtml> cellWidget = new CellWidget<SafeHtml>(cell);		
		SafeHtmlBuilder builder = new SafeHtmlBuilder();
		builder.appendEscaped(IMAGE_URL);
		cellWidget.setValue(builder.toSafeHtml());				
		return cellWidget;
	}

	
	interface Resources extends ClientBundle {
		  @Source("delete.png")
		  ImageResource delete();
	}
	
	public static CellWidget<String> buildIconCellDecorator() {
		Resources res = GWT.create(Resources.class);
		Cell<String> cell = new TextCell();
		IconCellDecorator<String> decoratorCell = new IconCellDecorator<String>(res.delete(), cell);
		final CellWidget<String> cellWidget = new CellWidget<String>(decoratorCell);		
		cellWidget.setValue("Some Tag Value");
		return cellWidget;
	}
	
}
