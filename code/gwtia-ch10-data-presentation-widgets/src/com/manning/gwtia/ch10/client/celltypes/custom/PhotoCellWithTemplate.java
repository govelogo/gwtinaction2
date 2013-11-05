package com.manning.gwtia.ch10.client.celltypes.custom;


import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.manning.gwtia.ch10.client.cellwidget.common.CalendarFactory;
import com.manning.gwtia.ch10.shared.PhotoDetails;

public class PhotoCellWithTemplate extends AbstractCell<PhotoDetails> {

	interface Template extends SafeHtmlTemplates {
		@Template("<table>" +
				     "<tr height=40px>" +
				         "<td width=80px rowspan=2 bgcolor=\"{1}\"/>&nbsp</td>" +
				         "<td>{0}</td>" +
				      "</tr>" +
				      "<tr>" +
				         "<td>Taken: {2}</td>" +
				      "</tr>" +
				   "</table>")
		SafeHtml photo(String title, String Image, String date);
	}

	private static Template template;

	public PhotoCellWithTemplate() {
		super(BrowserEvents.CLICK);
		if (template == null) {
			template = GWT.create(Template.class);
		}
	}

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			PhotoDetails value, SafeHtmlBuilder sb) {
		if(value!=null)
			sb.append(template.photo(value.getTitle(), value.getLargeUrl(), CalendarFactory.getDisplayFormatter().format(value.getDate())));
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
