package com.manning.gwtia.ch16.client.di;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.manning.gwtia.ch16.client.di.PhotoAppGinModule;
import com.manning.gwtia.ch16.client.di.presenters.PhotoDetailsPresenter;
import com.manning.gwtia.ch16.client.di.presenters.PhotoListPresenter;
import com.manning.gwtia.ch16.client.di.presenters.WelcomePresenter;
import com.manning.gwtia.ch16.client.di.views.PhotoDetailsView;
import com.manning.gwtia.ch16.client.di.views.PhotoListView;
import com.manning.gwtia.ch16.client.di.views.WelcomeView;
import com.manning.gwtia.ch16.shared.PhotoAlbumServiceAsync;

@GinModules(PhotoAppGinModule.class)
public interface PhotoAppGinjector extends Ginjector{
	EventBus getEventBus();
	PhotoAlbumServiceAsync getPhotoServices();
	PhotoDetailsView getPhotoView();
	PhotoListView getListView();
	WelcomeView getWelcomeView();
	WelcomePresenter getWelcomePresenter();
	PhotoListPresenter getPhotoListPresenter();
	PhotoDetailsPresenter getPhotoDetailsPresenter();
}
