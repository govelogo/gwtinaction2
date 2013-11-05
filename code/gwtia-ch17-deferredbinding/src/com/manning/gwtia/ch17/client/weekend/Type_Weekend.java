package com.manning.gwtia.ch17.client.weekend;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.manning.gwtia.ch17.client.weekend.stuff.Resources;


public class Type_Weekend extends Type{
	Type_Weekend() {
		super("Weekend");
		Resources res = GWT.create(Resources.class);
		res.theCss().ensureInjected();
		HTMLPanel p = new HTMLPanel("<iframe width=\"420\" height=\"315\" src=\"http://www.youtube.com/embed/RFzyYYZsxGc?autoplay=1\" frameborder=\"0\" allowfullscreen></iframe>");
		RootPanel.get().add(p, (Window.getClientWidth()/2)-210, (Window.getClientHeight()/2)-140);
	}
}
