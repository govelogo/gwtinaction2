package com.manning.gwtia.ch15.client.mvp.activities;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.manning.gwtia.ch15.client.mvp.ClientFactory;
import com.manning.gwtia.ch15.client.mvp.places.HasBreadCrumb;
import com.manning.gwtia.ch15.client.mvp.places.WelcomePlace;
import com.manning.gwtia.ch15.client.mvp.views.BreadCrumbView;

public class BreadCrumbActivity extends AbstractActivity {

	private ClientFactory clientFactory;
	private BreadCrumbView breadcrumbView;
	private Place place;

	public BreadCrumbActivity(Place place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.place = place;
	}

	public void start(AcceptsOneWidget panel, com.google.gwt.event.shared.EventBus eventBus) {
		breadcrumbView = clientFactory.getBreadCrumbView();
		breadcrumbView.setText(((HasBreadCrumb) place).getBreadCrumb());
		panel.setWidget(breadcrumbView.asWidget());
		breadcrumbView.setVisible(!(place instanceof WelcomePlace));
	}

    public void goTo(Place place) {
        clientFactory.getPlaceController().goTo(place);
    }
    
    /**
     * Adding a warning to be displayed by the breadcrumb when navigating away from 
     * the PhotoDetailsPlace.
     * It makes little functional sense, but it is here to show how the ordering of
     * defining ActivityManagers in PhotoAlbumApp affects which Activity gets to 
     * show its message to the user when navigating away.
     */
    @Override
    public String mayStop() {
    	return null;
//    	if(clientFactory.getPlaceController().getWhere() instanceof PhotoDetailsPlace)
//    		return "Please hold on. Breadcrumb activity is stopping.";
//    	else return null;
    }

}
