package com.manning.gwtia.ch10.client.celltypes.custom;

import java.util.List;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CompositeCell;
import com.google.gwt.cell.client.HasCell;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.manning.gwtia.ch10.shared.PhotoDetails;

public class VerticalCompositeCell extends CompositeCell<PhotoDetails>{

	public VerticalCompositeCell(List<HasCell<PhotoDetails, ?>> hasCells) {
		super(hasCells);
	}

	@Override
	public void render(Context context, PhotoDetails value,
			SafeHtmlBuilder sb) {
		sb.appendHtmlConstant("<table><tbody>");
		super.render(context, value, sb);
		sb.appendHtmlConstant("</tbody></table>");
	}

	@Override
	protected Element getContainerElement(Element parent) {
		// Return the first TR element in the table.
		return parent.getFirstChildElement().getFirstChildElement()
				.getFirstChildElement();
	}

	@Override
	protected <X> void render(Context context, PhotoDetails value,
			SafeHtmlBuilder sb, HasCell<PhotoDetails, X> hasCell) {
		Cell<X> cell = hasCell.getCell();
		sb.appendHtmlConstant("<tr><td>");
		cell.render(context, hasCell.getValue(value), sb);
		sb.appendHtmlConstant("</td></tr>");
	}	
}
