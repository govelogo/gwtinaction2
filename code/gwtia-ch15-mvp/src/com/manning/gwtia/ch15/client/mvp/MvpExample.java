package com.manning.gwtia.ch15.client.mvp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;
import com.manning.gwtia.ch15.shared.PhotoAlbumServiceAsync;

public class MvpExample extends Composite{

	ClientFactory clientFactory = GWT.create(ClientFactory.class);
	SimplePanel container = new SimplePanel();
	
	// This would be the onModuleLoad method of a normal application (but as we wrap into our GWTiA framework, it sits on its own)
	public MvpExample(){
	    EventBus eventBus = clientFactory.getEventBus();
	    PhotoAlbumServiceAsync rpcService = clientFactory.getPhotoServices();
	    PhotoAlbumApp app = new PhotoAlbumApp(rpcService, eventBus);
	    initWidget(container);
	    container.setSize("100%","100%");
	    app.go(container);
	    History.fireCurrentHistoryState();
	}
}
