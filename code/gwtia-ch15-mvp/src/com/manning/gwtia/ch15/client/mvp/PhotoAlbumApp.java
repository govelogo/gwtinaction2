package com.manning.gwtia.ch15.client.mvp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.web.bindery.event.shared.EventBus;
import com.manning.gwtia.ch15.client.mvp.events.AppBusyEvent;
import com.manning.gwtia.ch15.client.mvp.events.AppBusyHandler;
import com.manning.gwtia.ch15.client.mvp.events.AppFreeEvent;
import com.manning.gwtia.ch15.client.mvp.events.AppFreeHandler;
import com.manning.gwtia.ch15.client.mvp.presenters.impl.PhotoDetailsPresenterImpl;
import com.manning.gwtia.ch15.client.mvp.presenters.impl.PhotoListPresenterImpl;
import com.manning.gwtia.ch15.client.mvp.presenters.impl.WelcomePresenterImpl;
import com.manning.gwtia.ch15.client.mvp.ui.BusyIndicator;
import com.manning.gwtia.ch15.shared.PhotoAlbumServiceAsync;

public class PhotoAlbumApp implements ValueChangeHandler<String> {

	EventBus eventBus;
	PhotoAlbumServiceAsync rpcService;
	ClientFactory clientFactory = GWT.create(ClientFactory.class);


	public PhotoAlbumApp(PhotoAlbumServiceAsync rpcService, EventBus eventBus) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
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
				//History.newItem(Tokens.LIST + "&" + page, false);
				new PhotoListPresenterImpl(clientFactory.getListView())
						.go(container);
	}

	// Display and handle the Editing of a Photo's details
	private void doPhotoDetailsEdit(final String photoId) {
				new PhotoDetailsPresenterImpl(
						clientFactory.getPhotoView(), photoId).go(container);

	}

	// Display the Welcome Screen
	private void doWelcome() {
				new WelcomePresenterImpl(clientFactory.getWelcomeView())
						.go(container);
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

	HasWidgets container;

	public void go(HasWidgets container) {
		this.container = container;
	}
}
