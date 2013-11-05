package com.manning.gwtia.ch15.client.mvp.activities;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.manning.gwtia.ch15.client.mvp.ClientFactory;
import com.manning.gwtia.ch15.client.mvp.places.PhotoListPlace;
import com.manning.gwtia.ch15.client.mvp.places.PhotoListTextPlace;
import com.manning.gwtia.ch15.client.mvp.places.WelcomePlace;
import com.manning.gwtia.ch15.client.mvp.presenters.WelcomePresenter;
import com.manning.gwtia.ch15.client.mvp.views.WelcomeView;

public class WelcomeActivity extends AbstractActivity implements WelcomePresenter {

	private ClientFactory clientFactory;
	private WelcomeView welcomeView;

	public WelcomeActivity(WelcomePlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	public void start(AcceptsOneWidget panel, com.google.gwt.event.shared.EventBus eventBus) {
		welcomeView = clientFactory.getWelcomeView();
		panel.setWidget(welcomeView.asWidget());
		bind();		
	}

	public void bind() {
		welcomeView.setPresenter(this);
	}

	public void onshowPhotosButtonClicked() {
		goTo(new PhotoListPlace("0"));
	}
	
	public void onshowPhotosTextButtonClicked() {
		goTo(new PhotoListTextPlace("0"));
	}
	
    public void goTo(Place place) {
        clientFactory.getPlaceController().goTo(place);
    }

}
