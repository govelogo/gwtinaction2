package com.manning.gwtia.ch16.client.di.views.test.impl;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch16.client.di.views.PhotoListView;
import com.manning.gwtia.ch16.client.di.views.test.impl.PhotoListViewImpl_TEST;
import com.manning.gwtia.ch16.client.di.presenters.PhotoListPresenter;
import com.manning.gwtia.ch16.client.di.ui.PhotoWidget;
import com.manning.gwtia.ch16.shared.PhotoDetails;

public class PhotoListViewImpl_TEST extends Composite implements PhotoListView {
	
	interface PhotoListViewUiBinder extends UiBinder<Widget, PhotoListViewImpl_TEST> {}
	private static PhotoListViewUiBinder uiBinder = GWT.create(PhotoListViewUiBinder.class);
	
	
	  private PhotoListPresenter presenter;
	  @UiField FlexTable display;
	  @UiField ScrollPanel container;
	  
	  public PhotoListViewImpl_TEST() {
		initWidget(uiBinder.createAndBindUi(this));
	    Window.enableScrolling(false);
	  }
	  
	  public void setPresenter(PhotoListPresenter presenter) {
	    this.presenter = presenter;
	  }

	  @SuppressWarnings("deprecation")
	@UiHandler("container")
	  public void scrolling(ScrollEvent event) {
		  if (container.getScrollPosition() == (display.getOffsetHeight() - container.getOffsetHeight()))
			  presenter.onNewPhotosNeeded();
	  }

	  int MAXWIDTH=2;
	  static int row = 0;
	  static int col = 0;
	  PhotoWidget thePhoto;
	  
	public void addPictures(Vector<PhotoDetails> photos) {

		for (final PhotoDetails photo: photos){
			Label title = new Label(photo.getId() + " " + photo.getTitle());
			display.setWidget(row++, col, title);
		}
		
//		for (final PhotoDetails photo: photos){
//			thePhoto = new PhotoWidget(photo.getTitle(), photo.getTags(), photo.getThubnailUrl());
//			thePhoto.addClickHandler(new ClickHandler(){
//				public void onClick(ClickEvent event) {
//					presenter.onSelectPhotoClicked(photo.getId());
//				}
//			});
//			display.setWidget(row, col, thePhoto);
//			if (++col==MAXWIDTH){
//				col = 0;
//				row++;
//			}
//		}
	}
}
