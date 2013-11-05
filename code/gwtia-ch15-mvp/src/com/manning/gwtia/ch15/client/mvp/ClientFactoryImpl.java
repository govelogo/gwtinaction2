package com.manning.gwtia.ch15.client.mvp;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

import com.manning.gwtia.ch15.client.mvp.views.PhotoDetailsView;
import com.manning.gwtia.ch15.client.mvp.views.PhotoListView;
import com.manning.gwtia.ch15.client.mvp.views.WelcomeView;
import com.manning.gwtia.ch15.client.mvp.views.hardcoded.PhotoDetailsViewImpl;
import com.manning.gwtia.ch15.client.mvp.views.hardcoded.PhotoListViewImpl;
import com.manning.gwtia.ch15.client.mvp.views.hardcoded.WelcomeViewImpl;
import com.manning.gwtia.ch15.shared.PhotoAlbumService;
import com.manning.gwtia.ch15.shared.PhotoAlbumServiceAsync;

public class ClientFactoryImpl implements ClientFactory{

	private static EventBus eventBus;
	private static PhotoAlbumServiceAsync rpcService;
	private static PhotoDetailsView detailView;
	private static PhotoListView listView;
	private static WelcomeView welcomeView;
	 
	
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
}
