package com.manning.gwtia.ch15.client.mvp.views.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch15.client.mvp.presenters.WelcomePresenter;
import com.manning.gwtia.ch15.client.mvp.views.WelcomeView;

public class WelcomeViewImpl extends Composite implements WelcomeView {
	
	interface WelcomeViewUiBinder extends UiBinder<Widget, WelcomeViewImpl> {}
	private static WelcomeViewUiBinder uiBinder = GWT.create(WelcomeViewUiBinder.class);
	
	
	  private WelcomePresenter presenter;
	  @UiField Button showPhotos;
	  @UiField Button showPhotosText;
	  
	  public WelcomeViewImpl() {
			initWidget(uiBinder.createAndBindUi(this));
	  }
	  
	  @UiHandler("showPhotos")
	  public void showList(ClickEvent event) {
	        if (presenter != null) {
		          presenter.onshowPhotosButtonClicked();
		        }
	  }
	  
	  @UiHandler("showPhotosText")
	  public void showListText(ClickEvent event) {
	        if (presenter != null) {
		          presenter.onshowPhotosTextButtonClicked();
		        }
	  }


	public void setPresenter(WelcomePresenter presenter) {
	    this.presenter = presenter;		
	}
}
