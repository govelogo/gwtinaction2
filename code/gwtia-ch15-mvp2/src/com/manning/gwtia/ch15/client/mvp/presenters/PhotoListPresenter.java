package com.manning.gwtia.ch15.client.mvp.presenters;

import com.manning.gwtia.ch15.client.mvp.MyDataProvider;
import com.manning.gwtia.ch15.shared.PhotoDetails;

public interface PhotoListPresenter {
	  public void onSelectPhotoClicked(String id);

	public void onNewPhotosNeeded();

	public MyDataProvider getDataProvider();

	public void switchListView();
	public void switchGridView();

	public void savePhoto(PhotoDetails object);
}
