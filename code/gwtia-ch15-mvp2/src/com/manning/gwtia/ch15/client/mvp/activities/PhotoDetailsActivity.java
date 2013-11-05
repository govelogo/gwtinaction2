package com.manning.gwtia.ch15.client.mvp.activities;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.manning.gwtia.ch15.client.mvp.ClientFactory;
import com.manning.gwtia.ch15.client.mvp.events.AppBusyEvent;
import com.manning.gwtia.ch15.client.mvp.events.AppFreeEvent;
import com.manning.gwtia.ch15.client.mvp.places.PhotoDetailsPlace;
import com.manning.gwtia.ch15.client.mvp.presenters.PhotoDetailsPresenter;
import com.manning.gwtia.ch15.client.mvp.views.PhotoDetailsView;
import com.manning.gwtia.ch15.shared.PhotoAlbumServiceAsync;
import com.manning.gwtia.ch15.shared.PhotoDetails;

public class PhotoDetailsActivity implements  Activity, PhotoDetailsPresenter {

	private ClientFactory clientFactory;
	private PhotoDetails photoDetails;
	private String id;
	private PhotoAlbumServiceAsync rpcService;
	private EventBus appEventBus;
	private PhotoDetailsView photoDetailsView;
	private Request currHttpRequest;
	
	public void start(final AcceptsOneWidget panel, final com.google.gwt.event.shared.EventBus eventBus) {

		this.photoDetailsView = clientFactory.getPhotoView();
		bind();

		appEventBus.fireEvent(new AppBusyEvent());
		currHttpRequest = rpcService.getPhotoDetails(id,
				new AsyncCallback<PhotoDetails>() {
					public void onSuccess(PhotoDetails result) {
						photoDetails = result;
						photoDetailsView.getPhotoTitle().setValue(photoDetails.getTitle());
						photoDetailsView.getPhotoTags().setValue(photoDetails.getTags());
						photoDetailsView.setPhoto(photoDetails.getLargeUrl());
						appEventBus.fireEvent(new AppFreeEvent());
						panel.setWidget(photoDetailsView.asWidget());
						madeEdits = false;
						initialised = true;
					}

					public void onFailure(Throwable caught) {
						appEventBus.fireEvent(new AppFreeEvent());
					}
				});
	}
	
	public void onStop(){
		if(photoDetailsView!=null)photoDetailsView.setPhoto("");
	}
	
	boolean madeEdits = false;
	boolean initialised = false;


	public String mayStop() {
		if (madeEdits) return "Are you happy with the edits made to the photo?";
		else return null;
	}

	public void onCancel() {
		if ((currHttpRequest!=null)&&(currHttpRequest.isPending())){
			currHttpRequest.cancel();
			Window.alert("Cancelling RPC");
		}
	}
	
	public PhotoDetailsActivity(PhotoDetailsPlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.id = place.getPhotoId();
		// Get and link up the necessary aspects
		this.appEventBus = clientFactory.getEventBus();
		this.rpcService = clientFactory.getPhotoServices();
	}

	public void bind() {
		photoDetailsView.setPresenter(this);
	}

	public void onUpdatePhoto() {
		if(!initialised) return;
		photoDetails.setTitle(photoDetailsView.getPhotoTitle().getValue());
		photoDetails.setTags(photoDetailsView.getPhotoTags().getValue());
		appEventBus.fireEvent(new AppBusyEvent());
		rpcService.updatePhotoDetails(photoDetails,
				new AsyncCallback<PhotoDetails>() {
					public void onSuccess(PhotoDetails result) {
						madeEdits = true;
						Scheduler.get().scheduleFixedDelay(new RepeatingCommand(){
							public boolean execute() {
								appEventBus.fireEvent(new AppFreeEvent());
								return false;
							}
							
						}, 1500);
					}

					public void onFailure(Throwable caught) {
						Scheduler.get().scheduleFixedDelay(new RepeatingCommand(){
							public boolean execute() {
								appEventBus.fireEvent(new AppFreeEvent());
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
