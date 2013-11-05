package com.manning.gwtia.ch15.client.mvp.activities;


import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.manning.gwtia.ch15.client.mvp.ClientFactory;
import com.manning.gwtia.ch15.client.mvp.MyDataProvider;
import com.manning.gwtia.ch15.client.mvp.events.AppFreeEvent;
import com.manning.gwtia.ch15.client.mvp.places.PhotoDetailsPlace;
import com.manning.gwtia.ch15.client.mvp.places.PhotoListPlace;
import com.manning.gwtia.ch15.client.mvp.places.PhotoListTextPlace;
import com.manning.gwtia.ch15.client.mvp.presenters.PhotoListPresenter;
import com.manning.gwtia.ch15.client.mvp.views.PhotoListTextView;
import com.manning.gwtia.ch15.shared.PhotoAlbumServiceAsync;
import com.manning.gwtia.ch15.shared.PhotoDetails;

public class PhotoListTextActivity extends AbstractActivity implements PhotoListPresenter {

	private ClientFactory clientFactory;
	private PhotoListTextView photoListTextView;
	private PhotoAlbumServiceAsync rpcService;
	private EventBus appEventBus;

	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		photoListTextView = clientFactory.getListTextView();
		this.onNewPhotosNeeded();
		bind();
		panel.setWidget(photoListTextView.asWidget());
	}
	
	public PhotoListTextActivity(PhotoListTextPlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.rpcService = clientFactory.getPhotoServices();
		this.appEventBus = clientFactory.getEventBus();
	}

	public void bind() {
		photoListTextView.setPresenter(this);
	}

	public void onSelectPhotoClicked(String id) {
		goTo(new PhotoDetailsPlace(id));
	}
	
    public void goTo(Place place) {
        clientFactory.getPlaceController().goTo(place);
    }

    // Empty implementation as this is controlled by the view in this case. 
	public void onNewPhotosNeeded(){
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
		rpcService.updatePhotoDetails(object, new AsyncCallback<PhotoDetails>(){

			public void onFailure(Throwable caught) {
				Window.alert("Error");
				appEventBus.fireEvent(new AppFreeEvent());
			}

			@Override
			public void onSuccess(PhotoDetails result) {
				appEventBus.fireEvent(new AppFreeEvent());				
			}
		});
	}
	
}
