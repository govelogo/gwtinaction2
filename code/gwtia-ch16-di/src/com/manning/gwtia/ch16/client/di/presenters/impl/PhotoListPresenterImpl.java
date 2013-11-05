package com.manning.gwtia.ch16.client.di.presenters.impl;

import java.util.Vector;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import com.manning.gwtia.ch16.client.di.presenters.PhotoListPresenter;
import com.manning.gwtia.ch16.client.di.Tokens;
import com.manning.gwtia.ch16.client.di.events.AppBusyEvent;
import com.manning.gwtia.ch16.client.di.events.AppFreeEvent;
import com.manning.gwtia.ch16.client.di.views.PhotoListView;
import com.manning.gwtia.ch16.shared.PhotoAlbumServiceAsync;
import com.manning.gwtia.ch16.shared.PhotoDetails;

public class PhotoListPresenterImpl implements PhotoListPresenter {

	private PhotoAlbumServiceAsync rpcService;
	@Inject private EventBus eventBus;
	private PhotoListView photoListView;

	@SuppressWarnings("unused")
	@Inject
	private void setUpRPC(PhotoAlbumServiceAsync rpcService){
		this.rpcService = rpcService;
	}
	
	@Inject
	public PhotoListPresenterImpl(PhotoListView photoListView /*, EventBus eventBus, PhotoAlbumServiceAsync rpcService*/) {
		//this.rpcService = rpcService;
		//this.eventBus = eventBus;
		this.photoListView = photoListView;
		bind();
	}

	public void bind() {
		photoListView.setPresenter(this);
	}

	public void go(final HasWidgets container) {
		container.clear();
		container.add(photoListView.asWidget());
	}

	public void onSelectPhotoClicked(String id) {
		History.newItem(Tokens.DETAIL + "&" + id);
	}

	int START = 0;
	int LENGTH = 12;
	
	public void onNewPhotosNeeded(){
		eventBus.fireEvent(new AppBusyEvent());
		rpcService.getPhotoList(START, LENGTH,
				new AsyncCallback<Vector<PhotoDetails>>() {
					public void onSuccess(Vector<PhotoDetails> result) {
						photoListView.addPictures(result);
						eventBus.fireEvent(new AppFreeEvent());
					}

					public void onFailure(Throwable caught) {
						Window.alert("Error");
						eventBus.fireEvent(new AppFreeEvent());
					}
				});
	}
}
