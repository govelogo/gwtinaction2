package com.manning.gwtia.ch16.client.di.presenters.impl;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import com.manning.gwtia.ch16.client.di.presenters.PhotoDetailsPresenter;
import com.manning.gwtia.ch16.client.di.events.AppBusyEvent;
import com.manning.gwtia.ch16.client.di.events.AppFreeEvent;
import com.manning.gwtia.ch16.client.di.views.PhotoDetailsView;
import com.manning.gwtia.ch16.shared.PhotoAlbumServiceAsync;
import com.manning.gwtia.ch16.shared.PhotoDetails;

public class PhotoDetailsPresenterImpl implements PhotoDetailsPresenter {

	private PhotoDetails photoDetails;
	private String id;
	private PhotoAlbumServiceAsync rpcService;
	//private final EventBus eventBus;
	@Inject private EventBus eventBus;
	private final PhotoDetailsView photoDetailsView;
	
	
	@SuppressWarnings("unused")
	@Inject
	private void setUpRPC(PhotoAlbumServiceAsync rpcService){
		this.rpcService = rpcService;
	}
	
	public void setPhoto(String photoId) {
		this.id = photoId;
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
	}
	
	@Inject
	public PhotoDetailsPresenterImpl(PhotoDetailsView photoDetailsView /*EventBus eventBus, PhotoAlbumServiceAsync rpcService*/) {
		//this.rpcService = rpcService;
		//this.eventBus = eventBus; 
		this.photoDetailsView = photoDetailsView;
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
