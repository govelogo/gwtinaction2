package com.manning.gwtia.ch17.client.weekend;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

public class PropertyExample extends Composite {

	interface PropertyExampleUiBinder extends UiBinder<Widget, PropertyExample> {}
	private static PropertyExampleUiBinder uiBinder = GWT.create(PropertyExampleUiBinder.class);

	@UiField DatePicker theDate;
	@UiField Label classUsed;
	@UiField Button forceError;

	public PropertyExample() {
		initWidget(uiBinder.createAndBindUi(this));
		Type type = (Type)GWT.create(Type.class);
		classUsed.setText(classUsed.getText()+type.getVal());
		setDate();
	}
	
	void setDate(){
		String date = Window.Location.getParameter("date");
		if(date!=null&&!date.equals("")){
			String[] bits = date.split("/");
			Date d = new Date();
			d.setYear(Integer.parseInt(bits[0])-1900);
			d.setMonth(Integer.parseInt(bits[1])-1);
			d.setDate(Integer.parseInt(bits[2]));
			theDate.setValue(d, false);
		}
	}
	
	@UiHandler("theDate")
	void setNewDate(ValueChangeEvent<Date> event){
		Date date = event.getValue();
		int day = date.getDate();
		String dayString = day<10?"0"+day:""+day;
		int month = date.getMonth()+1;
		String monthString = month<10?"0"+month:""+month;
		String year = ""+(date.getYear()+1900);
		String dateString = year+"/"+month+"/"+day;
		reload("date", dateString);
    }
	
	@UiHandler("forceError")
	void forceAnError(ClickEvent event){
		reload("forceError", "true");
	}

	private void reload(String whichParam, String param){	
		UrlBuilder loc = Window.Location.createUrlBuilder();
		loc.setParameter(whichParam, param);
		if(whichParam.equals("date")) loc.removeParameter("forceError");
		Window.Location.replace(loc.buildString());
	}
	
}
