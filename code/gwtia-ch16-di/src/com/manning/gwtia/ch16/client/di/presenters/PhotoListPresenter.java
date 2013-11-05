package com.manning.gwtia.ch16.client.di.presenters;

import com.manning.gwtia.ch16.client.di.presenters.Presenter;

public interface PhotoListPresenter extends Presenter{
	public void onSelectPhotoClicked(String id);
	public void onNewPhotosNeeded();
}
