package com.manning.gwtia.ch16.client.di.views.uibinder.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch16.client.di.views.WelcomeView;
import com.manning.gwtia.ch16.client.di.views.uibinder.impl.WelcomeViewImpl;
import com.manning.gwtia.ch16.client.di.presenters.WelcomePresenter;

public class WelcomeViewImpl extends Composite implements WelcomeView {
	
	interface WelcomeViewUiBinder extends UiBinder<Widget, WelcomeViewImpl> {}
	private static WelcomeViewUiBinder uiBinder = GWT.create(WelcomeViewUiBinder.class);
	
	
	  private WelcomePresenter presenter;
	  @UiField Button showPhotos;
	  
	  public WelcomeViewImpl() {
			initWidget(uiBinder.createAndBindUi(this));
	  }
	  
	  @UiHandler("showPhotos")
	  public void showList(ClickEvent event) {
	        if (presenter != null) {
		          presenter.onshowPhotosButtonClicked();
		        }
	  }


	public void setPresenter(WelcomePresenter presenter) {
	    this.presenter = presenter;		
	}
}
