package com.manning.gwtia.ch15.client.mvp.views;

import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.IsWidget;
import com.manning.gwtia.ch15.client.mvp.presenters.PhotoDetailsPresenter;

public interface PhotoDetailsView extends IsWidget{
	  HasValue<String> getPhotoTitle();
	  HasValue<String> getPhotoTags();
  	  void setPhoto(String largeUrl);
	  void setPresenter(PhotoDetailsPresenter presenter);
}
