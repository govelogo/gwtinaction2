package com.manning.gwtia.ch15.client.mvp.views.hardcoded;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch15.client.mvp.presenters.WelcomePresenter;
import com.manning.gwtia.ch15.client.mvp.ui.AppMenuBar;
import com.manning.gwtia.ch15.client.mvp.views.WelcomeView;

public class WelcomeViewImpl extends Composite implements WelcomeView {
	  private WelcomePresenter presenter;
	  private Button showPhotoList;
	  
	  public WelcomeViewImpl() {
		  DockLayoutPanel view = new DockLayoutPanel(Unit.PX);
		   AppMenuBar menu = new AppMenuBar();
		   showPhotoList = new Button("Show Photos");
		   FlowPanel fp = new FlowPanel();
		   fp.add(new Label("GWTiA MVP PhotoApp Example"));
		   fp.add(new Label("Click the Show Photos button below to see a grid of photos, click on a photo to do more"));
		   fp.add(showPhotoList);
		   view.addNorth(menu,25);
		   view.add(fp);
		   initWidget(view);
		   view.setSize("100%", "100%");
		   view.forceLayout();
		   bind();
	  }
	  
	  public Widget asWidget() {
	    return this;
	  }

	  public void bind() {
	    showPhotoList.addClickHandler(new ClickHandler() {   
	      public void onClick(ClickEvent event) {
	        if (presenter != null) {
	          presenter.onshowPhotosButtonClicked();
	        }
	      }
	    });
	  }

	public void setPresenter(WelcomePresenter presenter) {
	    this.presenter = presenter;		
	}
}
