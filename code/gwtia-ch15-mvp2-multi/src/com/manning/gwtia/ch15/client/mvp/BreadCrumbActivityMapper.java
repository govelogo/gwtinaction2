package com.manning.gwtia.ch15.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.manning.gwtia.ch15.client.mvp.activities.BreadCrumbActivity;

public class BreadCrumbActivityMapper implements ActivityMapper{

    private ClientFactory clientFactory;

    public BreadCrumbActivityMapper(ClientFactory clientFactory) {
        super();
        this.clientFactory = clientFactory;
    }

	public Activity getActivity(Place place) {
		return new BreadCrumbActivity(place, clientFactory);
	}

}
