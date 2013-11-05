package com.manning.gwtia.ch15.client.mvp.views.uibinder;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch15.client.mvp.presenters.PhotoListPresenter;
import com.manning.gwtia.ch15.client.mvp.ui.PhotoWidget;
import com.manning.gwtia.ch15.client.mvp.views.PhotoListView;
import com.manning.gwtia.ch15.shared.PhotoDetails;

public class PhotoListViewImpl extends Composite implements PhotoListView {
	
	interface PhotoListViewUiBinder extends UiBinder<Widget, PhotoListViewImpl> {}
	private static PhotoListViewUiBinder uiBinder = GWT.create(PhotoListViewUiBinder.class);
	
	private int pagesShown = 0;
	
	  private PhotoListPresenter presenter;
	  @UiField FlexTable display;
	  @UiField ScrollPanel container;
	  
	  public PhotoListViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	    Window.enableScrolling(false);
	  }
	  
	  public void setPresenter(PhotoListPresenter presenter) {
	    this.presenter = presenter;
	  }

	@UiHandler("container")
	  public void scrolling(ScrollEvent event) {
		  if (container.getVerticalScrollPosition() == (display.getOffsetHeight() - container.getOffsetHeight()))
			  presenter.onNewPhotosNeeded();
	  }
	
	public int getPagesShown(){
		return pagesShown;
	}

	  int MAXWIDTH=2;
	  static int row = 0;
	  static int col = 0;
	  PhotoWidget thePhoto;
	  
	public void addPictures(Vector<PhotoDetails> photos) {

		for (final PhotoDetails photo: photos){
			thePhoto = new PhotoWidget(photo.getTitle(), photo.getTags(), photo.getThubnailUrl());
			thePhoto.addClickHandler(new ClickHandler(){
				public void onClick(ClickEvent event) {
					presenter.onSelectPhotoClicked(photo.getId());
				}
			});
			display.setWidget(row, col, thePhoto);
			if (++col==MAXWIDTH){
				col = 0;
				row++;
			}
		}
		pagesShown++;
	}
}
