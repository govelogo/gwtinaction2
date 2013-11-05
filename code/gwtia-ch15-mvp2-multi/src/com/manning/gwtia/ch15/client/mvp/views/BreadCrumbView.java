package com.manning.gwtia.ch15.client.mvp.views;

import com.google.gwt.user.client.ui.IsWidget;

public interface BreadCrumbView extends IsWidget{
	public void setText(String text);

	public void setVisible(boolean b);
}
