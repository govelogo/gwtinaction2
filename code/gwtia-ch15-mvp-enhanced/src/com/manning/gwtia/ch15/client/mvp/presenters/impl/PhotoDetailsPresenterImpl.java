package com.manning.gwtia.ch15.client.mvp.presenters.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.manning.gwtia.ch15.client.mvp.ClientFactory;
import com.manning.gwtia.ch15.client.mvp.events.AppBusyEvent;
import com.manning.gwtia.ch15.client.mvp.events.AppFreeEvent;
import com.manning.gwtia.ch15.client.mvp.presenters.PhotoDetailsPresenter;
import com.manning.gwtia.ch15.client.mvp.views.PhotoDetailsView;
import com.manning.gwtia.ch15.shared.PhotoAlbumServiceAsync;
import com.manning.gwtia.ch15.shared.PhotoDetails;

public class PhotoDetailsPresenterImpl implements PhotoDetailsPresenter {

	private ClientFactory clientFactory = GWT.create(ClientFactory.class);
	private PhotoDetails photoDetails;
	private String id;
	private final PhotoAlbumServiceAsync rpcService;
	private final EventBus eventBus;
	private final PhotoDetailsView photoDetailsView;
	
	public String getId(){
		return this.id;
	}
	
	public PhotoDetailsPresenterImpl(final PhotoDetailsView photoDetailsView, final String id) {
		this.id = id;
		this.rpcService = clientFactory.getPhotoServices();
		this.eventBus = clientFactory.getEventBus();
		this.photoDetailsView = photoDetailsView;
		eventBus.fireEvent(new AppBusyEvent());
		rpcService.getPhotoDetails(id,
				new AsyncCallback<PhotoDetails>() {
					public void onSuccess(PhotoDetails result) {
						photoDetails = result;
						photoDetailsView.getPhotoTitle().setValue(photoDetails.getTitle());
						photoDetailsView.getPhotoTags().setValue(photoDetails.getTags());
						photoDetailsView.setPhoto(photoDetails.getLargeUrl());
						eventBus.fireEvent(new AppFreeEvent());
					}

					public void onFailure(Throwable caught) {
						eventBus.fireEvent(new AppFreeEvent());
					}
				});
		bind();
	}

	public void bind() {
		photoDetailsView.setPresenter(this);
	}

	public void go(final HasWidgets container) {
		container.clear();
		container.add(photoDetailsView.asWidget());
	}

	public void onUpdatePhoto() {
		photoDetails.setTitle(photoDetailsView.getPhotoTitle().getValue());
		photoDetails.setTags(photoDetailsView.getPhotoTags().getValue());
		eventBus.fireEvent(new AppBusyEvent());
		rpcService.updatePhotoDetails(photoDetails,
				new AsyncCallback<PhotoDetails>() {
					public void onSuccess(PhotoDetails result) {
						Scheduler.get().scheduleFixedDelay(new RepeatingCommand(){
							public boolean execute() {
								eventBus.fireEvent(new AppFreeEvent());
								return false;
							}
							
						}, 1500);
					}

					public void onFailure(Throwable caught) {
						Scheduler.get().scheduleFixedDelay(new RepeatingCommand(){
							public boolean execute() {
								eventBus.fireEvent(new AppFreeEvent());
								return false;
							}
							
						}, 500);
					}
				});
	}

	public void onUpdateTitle() {
		onUpdatePhoto();		
	}

	public void onUpdateTags() {
		onUpdatePhoto();
	}
}
