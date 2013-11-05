package com.manning.gwtia.ch15.client.mvp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.manning.gwtia.ch15.client.mvp.views.PhotoDetailsView;
import com.manning.gwtia.ch15.client.mvp.views.PhotoListTextView;
import com.manning.gwtia.ch15.client.mvp.views.PhotoListView;
import com.manning.gwtia.ch15.client.mvp.views.WelcomeView;
import com.manning.gwtia.ch15.client.mvp.views.uibinder.PhotoDetailsViewImpl;
import com.manning.gwtia.ch15.client.mvp.views.uibinder.PhotoListTextViewImpl;
import com.manning.gwtia.ch15.client.mvp.views.uibinder.PhotoListViewImpl;
import com.manning.gwtia.ch15.client.mvp.views.uibinder.WelcomeViewImpl;
import com.manning.gwtia.ch15.shared.PhotoAlbumService;
import com.manning.gwtia.ch15.shared.PhotoAlbumServiceAsync;

public class ClientFactoryImpl implements ClientFactory{

	private static EventBus eventBus;
	private static PhotoAlbumServiceAsync rpcService;
	private static PhotoDetailsView detailView;
	private static PhotoListView listView;
	private static PhotoListTextView listTextView;
	private static WelcomeView welcomeView;
	private static PlaceController placeController;
	 
	
	public EventBus getEventBus() {
		if (eventBus == null) eventBus = new SimpleEventBus();
		return eventBus;
	}

	public PhotoAlbumServiceAsync getPhotoServices() {
		if (rpcService == null) rpcService = GWT.create(PhotoAlbumService.class);
		return rpcService;
	}

	public PhotoDetailsView getPhotoView() {
		if (detailView == null) detailView = new PhotoDetailsViewImpl();
		return detailView;
	}

	public PhotoListView getListView() {
		if (listView == null) listView = new PhotoListViewImpl();
		return listView;
	}

	public WelcomeView getWelcomeView() {
		if (welcomeView == null) welcomeView = new WelcomeViewImpl();
		return welcomeView;
	}
	
	@Override
	public PhotoListTextView getListTextView() {
		if (listTextView == null) listTextView = new PhotoListTextViewImpl();
		return listTextView;	
	}

	/**
	 * Need to do some updating to reflect EventBus moving in GWT 2.4 causing the
	 * warning below.
	 */
	@Override
	public PlaceController getPlaceController() {
		if (placeController == null) placeController = new PlaceController(getEventBus());
		return placeController;
	}


}