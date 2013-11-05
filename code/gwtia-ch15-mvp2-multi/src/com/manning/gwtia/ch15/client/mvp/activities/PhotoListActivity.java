package com.manning.gwtia.ch15.client.mvp.activities;

import java.util.Vector;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.manning.gwtia.ch15.client.mvp.ClientFactory;
import com.manning.gwtia.ch15.client.mvp.events.AppBusyEvent;
import com.manning.gwtia.ch15.client.mvp.events.AppFreeEvent;
import com.manning.gwtia.ch15.client.mvp.places.PhotoDetailsPlace;
import com.manning.gwtia.ch15.client.mvp.places.PhotoListPlace;
import com.manning.gwtia.ch15.client.mvp.presenters.PhotoListPresenter;
import com.manning.gwtia.ch15.client.mvp.views.PhotoListView;
import com.manning.gwtia.ch15.shared.PhotoAlbumServiceAsync;
import com.manning.gwtia.ch15.shared.PhotoDetails;

public class PhotoListActivity extends AbstractActivity implements PhotoListPresenter {

	private ClientFactory clientFactory;
	private PhotoAlbumServiceAsync rpcService;
	private EventBus eventBus;
	private PhotoListView photoListView;

	public void start(AcceptsOneWidget panel, final com.google.gwt.event.shared.EventBus eventBus) {
		photoListView = clientFactory.getListView();
		this.rpcService = clientFactory.getPhotoServices();
		this.eventBus = clientFactory.getEventBus();
		if (photoListView.getPagesShown()==0) this.loadPhotos();
		if (photoListView.getPagesShown()!=page){
			for(int loop=photoListView.getPagesShown(); loop<page-1; loop++) this.loadPhotos();
		}
		bind();
		panel.setWidget(photoListView.asWidget());
	}
	
	private int page;
	
	public PhotoListActivity(PhotoListPlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		page = place.getPage();
	}

	public void bind() {
		photoListView.setPresenter(this);
	}

	public void onSelectPhotoClicked(String id) {
		goTo(new PhotoDetailsPlace(id));
	}
	
    public void goTo(Place place) {
        clientFactory.getPlaceController().goTo(place);
    }
    
    int START = 0;
    int LENGTH = 10;
    
    public void loadPhotos(){
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

	public void onNewPhotosNeeded(){
		loadPhotos();
		goTo(new PhotoListPlace(""+(++page)));
	}
}
