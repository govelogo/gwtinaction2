package com.manning.gwtia.ch16.client.di.presenters.impl;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import com.manning.gwtia.ch16.client.di.presenters.WelcomePresenter;
import com.manning.gwtia.ch16.client.di.Tokens;
import com.manning.gwtia.ch16.client.di.views.WelcomeView;

public class WelcomePresenterImpl implements WelcomePresenter {

	private final WelcomeView welcomeView;

	@Inject
	public WelcomePresenterImpl(WelcomeView welcomeView) {
		this.welcomeView = welcomeView;
		bind();
	}

	public void bind() {
		welcomeView.setPresenter(this);
		// Welcome page does not listen for any events.
	}

	public void go(final HasWidgets container) {
		container.clear();
		container.add(welcomeView.asWidget());
	}

	public void onshowPhotosButtonClicked() {
		History.newItem(Tokens.LIST);
	}
}
