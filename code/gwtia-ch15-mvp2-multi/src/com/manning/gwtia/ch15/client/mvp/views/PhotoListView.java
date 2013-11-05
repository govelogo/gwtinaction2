package com.manning.gwtia.ch15.client.mvp.views;

import java.util.Vector;

import com.google.gwt.user.client.ui.IsWidget;
import com.manning.gwtia.ch15.client.mvp.presenters.PhotoListPresenter;
import com.manning.gwtia.ch15.shared.PhotoDetails;

public interface PhotoListView extends IsWidget {
	  void setPresenter(PhotoListPresenter presenter);
	  void addPictures(Vector<PhotoDetails> photos);
	  public int getPagesShown();
}
