package com.manning.gwtia.ch16.client.di;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.manning.gwtia.ch16.client.di.PhotoAppGinjector;
import com.manning.gwtia.ch16.client.di.Tokens;
import com.manning.gwtia.ch16.client.di.events.AppBusyEvent;
import com.manning.gwtia.ch16.client.di.events.AppBusyHandler;
import com.manning.gwtia.ch16.client.di.events.AppFreeEvent;
import com.manning.gwtia.ch16.client.di.events.AppFreeHandler;
import com.manning.gwtia.ch16.client.di.presenters.PhotoDetailsPresenter;
import com.manning.gwtia.ch16.client.di.presenters.PhotoListPresenter;
import com.manning.gwtia.ch16.client.di.presenters.WelcomePresenter;
import com.manning.gwtia.ch16.client.di.ui.BusyIndicator;

public class PhotoAlbumApp implements ValueChangeHandler<String> {

	EventBus eventBus;
	HasWidgets container;

	private final PhotoAppGinjector injector = GWT.create(PhotoAppGinjector.class);


	public PhotoAlbumApp(){
		this.eventBus = injector.getEventBus();
		bind();
	}

	private void bind() {
		// Listen for History Change Events
		History.addValueChangeHandler(this);
		
		// Listen for AppBusy events on the event bus
		eventBus.addHandler(AppBusyEvent.getType(), 
				new AppBusyHandler(){
					public void onAppBusyEvent(AppBusyEvent event) {
						BusyIndicator.busy();
					}
		});
		
		// Listen for AppFree events on the event bus
		eventBus.addHandler(AppFreeEvent.getType(), 
				new AppFreeHandler(){
					public void onAppFreeEvent(AppFreeEvent event) {
						BusyIndicator.free();
					}
		});
	}
	
	
	// Display and handle the display of thumbnails. 
	private void doPhotoListDisplay(final String page){
		PhotoListPresenter pres = injector.getPhotoListPresenter();
		pres.onNewPhotosNeeded();
		pres.go(container);
	}

	// Display and handle the Editing of a Photo's details
	private void doPhotoDetailsEdit(final String photoId) {
		PhotoDetailsPresenter pres = injector.getPhotoDetailsPresenter();
		pres.setPhoto(photoId);
		pres.go(container);
	}
	

	// Display the Welcome Screen
	private void doWelcome() {
		WelcomePresenter pres = injector.getWelcomePresenter();
		pres.go(container);
	}


	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if ((token != null) && (!token.equals(Tokens.HOME))) {
			if (token.startsWith(Tokens.LIST)) {
				String[] bits = token.split("&");
				String page = "0";
				if (bits.length>1)page = bits[1];
				this.doPhotoListDisplay(page);
			} else if (token.contains(Tokens.DETAIL)) {
				String[] bits = token.split("&");
				doPhotoDetailsEdit(bits[1]);
			}
		} else {
			doWelcome();
		}
	}

	public void go(HasWidgets container) {
		this.container = container;
	}
}
