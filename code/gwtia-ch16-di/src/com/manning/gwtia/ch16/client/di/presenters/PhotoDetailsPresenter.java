package com.manning.gwtia.ch16.client.di.presenters;

import com.manning.gwtia.ch16.client.di.presenters.Presenter;

public interface PhotoDetailsPresenter extends Presenter{
	public void onUpdateTitle();
	public void onUpdateTags();
	public void setPhoto(String photoId);
}
