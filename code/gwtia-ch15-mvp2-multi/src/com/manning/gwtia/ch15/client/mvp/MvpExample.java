package com.manning.gwtia.ch15.client.mvp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;
import com.manning.gwtia.ch15.shared.PhotoAlbumServiceAsync;

public class MvpExample extends ResizeComposite{

	ClientFactory clientFactory = GWT.create(ClientFactory.class);
	SimplePanel main = new SimplePanel();
	SimplePanel breadcrumb = new SimplePanel();
	DockLayoutPanel container = new DockLayoutPanel(Unit.PX);

	
	// This would be the onModuleLoad method of a normal application (but as we wrap into our GWTiA framework, it sits on its own)
	public MvpExample(){
	    EventBus eventBus = clientFactory.getEventBus();
	    PhotoAlbumServiceAsync rpcService = clientFactory.getPhotoServices();
	    new PhotoAlbumApp(rpcService, eventBus, main, breadcrumb);
	    container.addNorth(breadcrumb, 25);
	    container.add(main);
	    initWidget(container);
	    container.setSize("100%","100%");
	    container.forceLayout();
	}
}
