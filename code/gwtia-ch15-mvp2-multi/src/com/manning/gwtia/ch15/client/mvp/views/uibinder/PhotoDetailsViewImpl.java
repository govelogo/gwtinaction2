package com.manning.gwtia.ch15.client.mvp.views.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch15.client.mvp.presenters.PhotoDetailsPresenter;
import com.manning.gwtia.ch15.client.mvp.ui.EditableLabel;
import com.manning.gwtia.ch15.client.mvp.ui.TagListWidget;
import com.manning.gwtia.ch15.client.mvp.views.PhotoDetailsView;

public class PhotoDetailsViewImpl extends Composite implements PhotoDetailsView {
	private PhotoDetailsPresenter presenter;
	
	interface PhotoDetailsViewUiBinder extends UiBinder<Widget, PhotoDetailsViewImpl> {}
	private static PhotoDetailsViewUiBinder uiBinder = GWT.create(PhotoDetailsViewUiBinder.class);
	
	@UiField EditableLabel title; 
	@UiField TagListWidget tags;
	//Would normally be an Image, but we use a FlowPanel here for effect
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
