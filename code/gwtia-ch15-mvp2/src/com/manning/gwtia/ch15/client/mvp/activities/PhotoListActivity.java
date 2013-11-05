package com.manning.gwtia.ch15.client.mvp.activities;

import java.util.Vector;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.manning.gwtia.ch15.client.mvp.ClientFactory;
import com.manning.gwtia.ch15.client.mvp.MyDataProvider;
import com.manning.gwtia.ch15.client.mvp.events.AppBusyEvent;
import com.manning.gwtia.ch15.client.mvp.events.AppFreeEvent;
import com.manning.gwtia.ch15.client.mvp.places.PhotoDetailsPlace;
import com.manning.gwtia.ch15.client.mvp.places.PhotoListPlace;
import com.manning.gwtia.ch15.client.mvp.places.PhotoListTextPlace;
import com.manning.gwtia.ch15.client.mvp.presenters.PhotoListPresenter;
import com.manning.gwtia.ch15.client.mvp.views.PhotoListView;
import com.manning.gwtia.ch15.shared.PhotoAlbumServiceAsync;
import com.manning.gwtia.ch15.shared.PhotoDetails;

public class PhotoListActivity extends AbstractActivity implements PhotoListPresenter {

	private ClientFactory clientFactory;
	private PhotoAlbumServiceAsync rpcService;
	private EventBus appEventBus;
	private PhotoListView photoListView;

	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		photoListView = clientFactory.getListView();
		this.onNewPhotosNeeded();
		bind();
		panel.setWidget(photoListView.asWidget());
	}
	
	public PhotoListActivity(PhotoListPlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.rpcService = clientFactory.getPhotoServices();
		this.appEventBus = clientFactory.getEventBus();
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

	public void onNewPhotosNeeded(){
		appEventBus.fireEvent(new AppBusyEvent());
		rpcService.getPhotoList(START,LENGTH,
				new AsyncCallback<Vector<PhotoDetails>>() {
					public void onSuccess(Vector<PhotoDetails> result) {
						photoListView.addPictures(result);
						appEventBus.fireEvent(new AppFreeEvent());
					}

					public void onFailure(Throwable caught) {
						Window.alert("Error");
						appEventBus.fireEvent(new AppFreeEvent());
					}
				});
	}

	@Override
	public MyDataProvider getDataProvider() {
		return new MyDataProvider(clientFactory);
	}

	@Override
	public void switchListView() {
		goTo(new PhotoListTextPlace("0"));
	}

	@Override
	public void switchGridView() {
		goTo(new PhotoListPlace("0"));
	}

	@Override
	public void savePhoto(PhotoDetails object) {
		// TODO Auto-generated method stub
		
	}
}
