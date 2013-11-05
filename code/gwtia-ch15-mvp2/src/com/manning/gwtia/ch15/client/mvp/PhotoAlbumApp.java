package com.manning.gwtia.ch15.client.mvp;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.manning.gwtia.ch15.client.mvp.events.AppBusyEvent;
import com.manning.gwtia.ch15.client.mvp.events.AppBusyHandler;
import com.manning.gwtia.ch15.client.mvp.events.AppFreeEvent;
import com.manning.gwtia.ch15.client.mvp.events.AppFreeHandler;
import com.manning.gwtia.ch15.client.mvp.places.WelcomePlace;
import com.manning.gwtia.ch15.client.mvp.ui.BusyIndicator;
import com.manning.gwtia.ch15.shared.PhotoAlbumServiceAsync;

public class PhotoAlbumApp{

	EventBus eventBus;
	PhotoAlbumServiceAsync rpcService;
	ClientFactory clientFactory = GWT.create(ClientFactory.class);

	
    private Place defaultPlace = new WelcomePlace();


	/**
	 * Need to do some updating to reflect EventBus moving in GWT 2.4 causing the
	 * warning below.
	 */
	public PhotoAlbumApp(PhotoAlbumServiceAsync rpcService, EventBus eventBus, AcceptsOneWidget appWidget) {
		
        PlaceController placeController = clientFactory.getPlaceController();

        // Start ActivityManager for the main widget with our ActivityMapper
        ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
        ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
        activityManager.setDisplay(appWidget);

        // Start PlaceHistoryHandler with our PlaceHistoryMapper
        AppPlacesHistoryMapper historyMapper= GWT.create(AppPlacesHistoryMapper.class);
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, defaultPlace);

        // Goes to the place represented on URL else default place
        historyHandler.handleCurrentHistory();
		
		
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		bind();
	}

	private void bind() {
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
}