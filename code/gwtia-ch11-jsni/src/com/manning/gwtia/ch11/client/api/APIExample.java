package com.manning.gwtia.ch11.client.api;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;


public class APIExample extends Composite {
	
	// UiBinder template
	interface APIExampleUiBinder extends UiBinder<Widget, APIExample> {}
	private static APIExampleUiBinder uiBinder = GWT.create(APIExampleUiBinder.class);
	
	@UiField
	static ListBox race;
	@UiField
	static FlowPanel enrolled;
	@UiField
	CheckBox publishChange;
	
	
	public static String getRace(){
		return race.getItemText(race.getSelectedIndex());
	}
	
	public static void enrolRace(String name, String race){
		enrolled.add(new Label(name + " (" + race +")"));
	}
	
	private native void setUpAPI()/*-{
		$wnd.getRace =
          $entry(@com.manning.gwtia.ch11.client.api.APIExample::getRace());
        $wnd.enrolRace =
          $entry(@com.manning.gwtia.ch11.client.api.APIExample::enrolRace(Ljava/lang/String;Ljava/lang/String;));
	}-*/;
	
	private native void publishChange()/*-{
	 	$wnd.raceChanged();
	}-*/;
	
	public APIExample() {
		initWidget(uiBinder.createAndBindUi(this));
		this.setSize("100%", "100%");
		setUpAPI();
		race.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				if (publishChange.getValue()) publishChange();
			}			
		});
	}
}
