package com.manning.gwtia.ch15.client.mvp.presenters.impl;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.manning.gwtia.ch15.client.mvp.Tokens;
import com.manning.gwtia.ch15.client.mvp.presenters.WelcomePresenter;
import com.manning.gwtia.ch15.client.mvp.views.WelcomeView;

public class WelcomePresenterImpl implements WelcomePresenter {

	private final WelcomeView welcomeView;

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
