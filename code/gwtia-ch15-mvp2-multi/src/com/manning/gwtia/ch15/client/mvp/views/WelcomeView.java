package com.manning.gwtia.ch15.client.mvp.views;

import com.google.gwt.user.client.ui.IsWidget;
import com.manning.gwtia.ch15.client.mvp.presenters.WelcomePresenter;

public interface WelcomeView extends IsWidget{
	void setPresenter(WelcomePresenter presenter);
}
