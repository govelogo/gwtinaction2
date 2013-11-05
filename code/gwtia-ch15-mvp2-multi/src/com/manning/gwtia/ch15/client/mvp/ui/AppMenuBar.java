package com.manning.gwtia.ch15.client.mvp.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.place.shared.Place;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch15.client.mvp.ClientFactory;
import com.manning.gwtia.ch15.client.mvp.places.PhotoListPlace;
import com.manning.gwtia.ch15.client.mvp.places.WelcomePlace;

public class AppMenuBar extends Composite{

	interface AppMenuBarUiBinder extends UiBinder<Widget, AppMenuBar> {}
	private static AppMenuBarUiBinder uiBinder = GWT.create(AppMenuBarUiBinder.class);
	private ClientFactory clientFactory = GWT.create(ClientFactory.class);

	public AppMenuBar(){
		initWidget(uiBinder.createAndBindUi(this));
		setUp();
	}
	
	@UiField MenuItem welcome;
	@UiField MenuItem list;
	@UiField MenuItem user;	
	@UiField MenuItem showEnglish;
	@UiField MenuItem showSwedish;
	
	
	
	private void changeLocale(String localeToUse){
		UrlBuilder newUrl = Window.Location.createUrlBuilder();
		newUrl.setParameter("locale", localeToUse);
		Window.Location.assign(newUrl.buildString());
	}

	
	private void setUp(){
		welcome.setCommand(new Command(){
			public void execute() {
				goTo(new WelcomePlace());
			}			
		});
		list.setCommand(new Command(){
			public void execute() {
				goTo(new PhotoListPlace("0"));
			}			
		});
		showEnglish.setCommand(new Command(){
			public void execute() {
				changeLocale("en");
			}
		});
		showSwedish.setCommand(new Command(){
			public void execute() {
				changeLocale("sv");
			}
		});
	}

    public void goTo(Place place) {
        clientFactory.getPlaceController().goTo(place);
    }
	
}
