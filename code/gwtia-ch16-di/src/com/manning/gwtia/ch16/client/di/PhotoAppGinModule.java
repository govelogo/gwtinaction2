package com.manning.gwtia.ch16.client.di;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.manning.gwtia.ch16.client.di.presenters.PhotoDetailsPresenter;
import com.manning.gwtia.ch16.client.di.presenters.PhotoListPresenter;
import com.manning.gwtia.ch16.client.di.presenters.WelcomePresenter;
import com.manning.gwtia.ch16.client.di.presenters.impl.PhotoDetailsPresenterImpl;
import com.manning.gwtia.ch16.client.di.presenters.impl.PhotoListPresenterImpl;
import com.manning.gwtia.ch16.client.di.presenters.impl.WelcomePresenterImpl;
import com.manning.gwtia.ch16.client.di.views.PhotoDetailsView;
import com.manning.gwtia.ch16.client.di.views.PhotoListView;
import com.manning.gwtia.ch16.client.di.views.WelcomeView;
import com.manning.gwtia.ch16.client.di.views.uibinder.impl.PhotoDetailsViewImpl;
import com.manning.gwtia.ch16.client.di.views.uibinder.impl.PhotoListViewImpl;
import com.manning.gwtia.ch16.client.di.views.uibinder.impl.WelcomeViewImpl;

public class PhotoAppGinModule extends AbstractGinModule{

	@Override
	protected void configure() {
		bind(WelcomeView.class).to(WelcomeViewImpl.class).in(Singleton.class);
		bind(PhotoDetailsView.class).to(PhotoDetailsViewImpl.class).in(Singleton.class);
		// Use next row for proper implementation
		bind(PhotoListView.class).to(PhotoListViewImpl.class).in(Singleton.class);
		// Use nest row for alternative view implementation
		//bind(PhotoListView.class).to(PhotoListViewImpl_TEST.class).in(Singleton.class);
		
		
		bind(WelcomePresenter.class).to(WelcomePresenterImpl.class);
		bind(PhotoDetailsPresenter.class).to(PhotoDetailsPresenterImpl.class);
		bind(PhotoListPresenter.class).to(PhotoListPresenterImpl.class);
		
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
	}
}
