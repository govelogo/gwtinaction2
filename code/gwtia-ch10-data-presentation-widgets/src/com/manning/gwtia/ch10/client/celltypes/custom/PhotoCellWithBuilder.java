package com.manning.gwtia.ch10.client.celltypes.custom;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.manning.gwtia.ch10.client.cellwidget.common.CalendarFactory;
import com.manning.gwtia.ch10.shared.PhotoDetails;

public class PhotoCellWithBuilder extends AbstractCell<PhotoDetails> {

	public PhotoCellWithBuilder() {
		super(BrowserEvents.CLICK);
	}

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			PhotoDetails value, SafeHtmlBuilder sb) {
		if(value!=null){
			sb.appendHtmlConstant("<table>");
			sb.appendHtmlConstant("<tr height=40px>");
			sb.appendHtmlConstant("<td width=80px rowspan=2 bgcolor=\""+value.getLargeUrl()+"\">&nbsp;</td>");
			sb.appendHtmlConstant("<td>");
			sb.appendEscaped(value.getTitle());
			sb.appendHtmlConstant("</td>");
			sb.appendHtmlConstant("</tr>");
			sb.appendHtmlConstant("<tr>");
			sb.appendHtmlConstant("<td>Taken: ");
			sb.appendEscaped(CalendarFactory.getDisplayFormatter().format(value.getDate()));
			sb.appendHtmlConstant("</td>");
			sb.appendHtmlConstant("</tr>");
			sb.appendHtmlConstant("</table>");
		}
	}
	
	@Override
	public void onBrowserEvent(Context context, 
            Element parent, 
            PhotoDetails value,
            NativeEvent event, 
            ValueUpdater<PhotoDetails> valueUpdater){
	    super.onBrowserEvent(context, parent, value, event, valueUpdater);
	    if (BrowserEvents.CLICK.equals(event.getType())) {
	    	if(valueUpdater!=null)valueUpdater.update(value);
	    	Window.alert("You clicked the cell with Index: " + context.getIndex());
	    }
	}

}
