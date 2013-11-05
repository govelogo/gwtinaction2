package com.manning.gwtia.ch15.client.mvp.views;

import com.google.gwt.user.client.ui.IsWidget;
import com.manning.gwtia.ch15.client.mvp.presenters.PhotoListPresenter;

public interface PhotoListTextView extends IsWidget{
	  void setPresenter(PhotoListPresenter presenter);
}
