package com.manning.gwtia.ch16.client.di.views;

import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch16.client.di.presenters.PhotoDetailsPresenter;

public interface PhotoDetailsView extends IsWidget{
	  HasValue<String> getPhotoTitle();
	  HasValue<String> getPhotoTags();
  	  void setPhoto(String largeUrl);
  	  public Widget asWidget();
	  void setPresenter(PhotoDetailsPresenter photoDetailsPresenter);
}
