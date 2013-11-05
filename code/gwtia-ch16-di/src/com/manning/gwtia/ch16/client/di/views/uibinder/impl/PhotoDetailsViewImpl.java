package com.manning.gwtia.ch16.client.di.views.uibinder.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch16.client.di.views.PhotoDetailsView;
import com.manning.gwtia.ch16.client.di.views.uibinder.impl.PhotoDetailsViewImpl;
import com.manning.gwtia.ch16.client.di.presenters.PhotoDetailsPresenter;
import com.manning.gwtia.ch16.client.di.ui.EditableLabel;
import com.manning.gwtia.ch16.client.di.ui.TagListWidget;

public class PhotoDetailsViewImpl extends Composite implements PhotoDetailsView {
	private PhotoDetailsPresenter presenter;
	
	interface PhotoDetailsViewUiBinder extends UiBinder<Widget, PhotoDetailsViewImpl> {}
	private static PhotoDetailsViewUiBinder uiBinder = GWT.create(PhotoDetailsViewUiBinder.class);
	
	@UiField EditableLabel title; 
	@UiField TagListWidget tags;
	@UiField FlowPanel thePhoto;
	
	public PhotoDetailsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		title.setHeight("30px");
		title.setWidth("100%");
		thePhoto.setHeight("560px");
		thePhoto.setWidth("720px");
	}
	
	public HasValue<String> getPhotoTitle() {
		return title;
	}

	public HasValue<String> getPhotoTags() {
		return tags;
	}

	public void setPresenter(PhotoDetailsPresenter presenter) {
		this.presenter = presenter;
	}

	@UiHandler("title")
	public void changeTitle(ValueChangeEvent<String> event) {
		if (presenter != null){
			presenter.onUpdateTitle();
		}
	}
	
	@UiHandler("tags")
	public void changeTags(ValueChangeEvent<String> event) {
		if (presenter != null){
			presenter.onUpdateTags();
		}
	}

	public void setPhoto(String url) {
		// Would normally set the url here, but we change the background colour in this example.
		//thePhoto.setUrl(url);
		thePhoto.getElement().getStyle().setBackgroundColor(url);
	}
}
