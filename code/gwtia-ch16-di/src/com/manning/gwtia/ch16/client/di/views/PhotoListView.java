package com.manning.gwtia.ch16.client.di.views;

import java.util.Vector;

import com.google.gwt.user.client.ui.IsWidget;
import com.manning.gwtia.ch16.client.di.presenters.PhotoListPresenter;
import com.manning.gwtia.ch16.shared.PhotoDetails;

public interface PhotoListView extends IsWidget {
	  void setPresenter(PhotoListPresenter presenter);
	  void addPictures(Vector<PhotoDetails> photos);
}
