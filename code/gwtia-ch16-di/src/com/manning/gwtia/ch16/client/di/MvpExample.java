package com.manning.gwtia.ch16.client.di;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.manning.gwtia.ch16.client.di.PhotoAlbumApp;

public class MvpExample extends Composite{
	
	SimplePanel container = new SimplePanel();
	
	// This would be the onModuleLoad method of a normal application (but as we wrap into our GWTiA framework, it sits on its own)
	public MvpExample(){
		PhotoAlbumApp app = new PhotoAlbumApp();
	    initWidget(container);
	    container.setSize("100%","100%");
	    app.go(container);
	    History.fireCurrentHistoryState();
	}
}
