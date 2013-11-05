package com.manning.gwtia.ch15.client.mvp.views.hardcoded;

import java.util.Vector;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch15.client.mvp.presenters.PhotoListPresenter;
import com.manning.gwtia.ch15.client.mvp.ui.AppMenuBar;
import com.manning.gwtia.ch15.client.mvp.ui.PhotoWidget;
import com.manning.gwtia.ch15.client.mvp.views.PhotoListView;
import com.manning.gwtia.ch15.shared.PhotoDetails;

public class PhotoListViewImpl extends Composite implements PhotoListView {
	  private PhotoListPresenter presenter;
	  private FlexTable display;
	  private ScrollPanel container;
	  
	  public PhotoListViewImpl() {
		DockLayoutPanel view = new DockLayoutPanel(Unit.PX);
		display = new FlexTable();
		container = new ScrollPanel();
		container.setSize("100%", "100%");
		container.add(display);
		view.addNorth(new AppMenuBar(), 25);
		view.add(container);
	    initWidget(view);
	    view.setSize("100%", "100%");
	    view.forceLayout();
	    Window.enableScrolling(false);
	    bind();
	  }
	  
	  public void setPresenter(PhotoListPresenter presenter) {
	    this.presenter = presenter;
	  }
	  
	  public Widget asWidget() {
	    return this;
	  }
	  
	  public void bind() {
		 container.addScrollHandler(new ScrollHandler(){
			@SuppressWarnings("deprecation")
			public void onScroll(ScrollEvent event) {
				if (container.getScrollPosition() == (display.getOffsetHeight() - container.getOffsetHeight())){
					presenter.onNewPhotosNeeded();
				}
			}
		 });
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
	}
}
