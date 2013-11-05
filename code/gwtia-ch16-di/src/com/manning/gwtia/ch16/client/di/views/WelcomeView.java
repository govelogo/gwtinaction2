package com.manning.gwtia.ch16.client.di.views;

import com.google.gwt.user.client.ui.IsWidget;
import com.manning.gwtia.ch16.client.di.presenters.WelcomePresenter;

public interface WelcomeView extends IsWidget{
	void setPresenter(WelcomePresenter presenter);
}
