package com.manning.gwtia.ch15.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.manning.gwtia.ch15.client.mvp.activities.PhotoDetailsActivity;
import com.manning.gwtia.ch15.client.mvp.activities.PhotoListActivity;
import com.manning.gwtia.ch15.client.mvp.activities.PhotoListTextActivity;
import com.manning.gwtia.ch15.client.mvp.activities.WelcomeActivity;
import com.manning.gwtia.ch15.client.mvp.places.PhotoDetailsPlace;
import com.manning.gwtia.ch15.client.mvp.places.PhotoListPlace;
import com.manning.gwtia.ch15.client.mvp.places.PhotoListTextPlace;
import com.manning.gwtia.ch15.client.mvp.places.WelcomePlace;

public class AppActivityMapper implements ActivityMapper{

    private ClientFactory clientFactory;

    public AppActivityMapper(ClientFactory clientFactory) {
        super();
        this.clientFactory = clientFactory;
    }

	public Activity getActivity(Place place) {
		if (place instanceof WelcomePlace)
			return new WelcomeActivity((WelcomePlace)place, clientFactory);
		else if (place instanceof PhotoListPlace)
				return new PhotoListActivity((PhotoListPlace)place, clientFactory);
		else if (place instanceof PhotoListTextPlace)
			return new PhotoListTextActivity((PhotoListTextPlace)place, clientFactory);
		else if (place instanceof PhotoDetailsPlace)
				return new PhotoDetailsActivity((PhotoDetailsPlace)place, clientFactory);
		else return null;
	}

}
