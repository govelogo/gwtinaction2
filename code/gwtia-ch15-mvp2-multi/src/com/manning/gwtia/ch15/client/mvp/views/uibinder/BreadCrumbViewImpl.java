package com.manning.gwtia.ch15.client.mvp.views.uibinder;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.manning.gwtia.ch15.client.mvp.views.BreadCrumbView;

public class BreadCrumbViewImpl extends Composite implements BreadCrumbView {
	  
	Label breadcrumb = new Label();
	
	  public BreadCrumbViewImpl() {
			initWidget(breadcrumb);
	  }	  
	  
	  public void setText(String breadCrumbText){
		  breadcrumb.setText(breadCrumbText);
	  }
}
