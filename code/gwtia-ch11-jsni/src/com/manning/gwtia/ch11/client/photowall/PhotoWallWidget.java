package com.manning.gwtia.ch11.client.photowall;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.manning.gwtia.ch11.client.photowall.event.InitializeHandler;
import com.manning.gwtia.ch11.client.photowall.event.ItemSelectedHandler;
import com.manning.gwtia.ch11.client.photowall.jsoverlay.FlashVars;
import com.manning.gwtia.ch11.client.photowall.jsoverlay.Item;
import com.manning.gwtia.ch11.client.photowall.jsoverlay.Params;

//http://www.cooliris.com/developer/reference/javascript/


public class PhotoWallWidget extends Composite{
	
	SimplePanel container;
	Cooliris theWall;
	
	final String ELEMENT_ID = "wall";
	final static String SWFOBJECT_SRC = "http://ajax.googleapis.com/ajax/libs/swfobject/2.2/swfobject.js";
	final static String COOLIRIS_SRC = "http://apps.cooliris.com/embed/cooliris.swf?t=1307582197";
	final String COOLIRIS_VERS = "9.0.0";
	String DEFAULT_FEED = "api://www.flickr.com/";
	FlashVars vars = FlashVars.newInstance();
	Params params =  Params.newInstance();
	
	String allowFullScreen = "false";
	String allowScriptAccess = "always";
	
	public PhotoWallWidget(){
		container = new SimplePanel();
		container.getElement().setId(ELEMENT_ID);
		initWidget(container);
		
		params.setAllowFullScreen(allowFullScreen);
		params.setAllowScriptAccess(allowScriptAccess);
		vars.setFeed(DEFAULT_FEED);
		vars.setIntro(FlashVars.INTRO.zoomIn);
		
		theWall = Cooliris.get();
		this.getElement().getStyle().setBackgroundColor("#cccccc");
	}
	
	public void onLoad(){
		Scheduler.get().scheduleDeferred(new Command() {
		    public void execute () {
		    	addBaseHandlers();
				embed();
		    }
		  });
	}
	
	Timer timer;
	
	private InitializeHandler initializeHandler = new InitializeHandler(){
		@Override
		public void onInitialized() {
			if (initializeHandler2!=null) initializeHandler2.onInitialized();
		}
	};
	
	private InitializeHandler initializeHandler2;
	
	private ItemSelectedHandler itemSelectedHandler = new ItemSelectedHandler(){
		@Override
		public void onItemSelected(Item item) {
			if (itemSelectedHandler2!=null)itemSelectedHandler2.onItemSelected(item);
		}
	};
	
	private ItemSelectedHandler itemSelectedHandler2;
	
	
	private void addBaseHandlers(){
		if (theWall!=null){
			theWall.addHandlers(initializeHandler, itemSelectedHandler);
			if (timer!=null){
				timer.cancel();
				timer = null;
			}
		} else {
			if (timer==null){
				timer = new Timer(){
					public void run() {
						addBaseHandlers();					
					}
				};
			}
			timer.schedule(5);
		}
	}
	
	public void addItemSelectedHandler(ItemSelectedHandler handler) {
		itemSelectedHandler2 = handler;
	}

	public void addInitializeHandler(InitializeHandler handler) {
		initializeHandler2 = handler;
	}
	
	public void setFeed(String feed){
		theWall.setFeed(feed);
	}
	

	public void embed(){
		Element bodyElement = RootPanel.getBodyElement();
		Element script = DOM.createElement("script");
		String oops = buildIt(vars,params);
		GWT.log(oops);
		script.setInnerText(buildIt(vars,params));
		bodyElement.appendChild(script);
	}
	
	private native String buildIt(FlashVars vars,  Params params)/*-{
		$doc.t_vars = vars;
		$doc.t_params = params;
		var text = "swfobject.embedSWF(\""
				   +@com.manning.gwtia.ch11.client.photowall.PhotoWallWidget::COOLIRIS_SRC+"\",\""
                   +this.@com.manning.gwtia.ch11.client.photowall.PhotoWallWidget::ELEMENT_ID+"\",\""
                   +this.@com.manning.gwtia.ch11.client.photowall.PhotoWallWidget::container.@com.google.gwt.user.client.ui.UIObject::getOffsetWidth()()+"\",\""
                   +this.@com.google.gwt.user.client.ui.UIObject::getOffsetHeight()()+"\",\""
                   +this.@com.manning.gwtia.ch11.client.photowall.PhotoWallWidget::COOLIRIS_VERS
                   +"\",\""+"\","
                   +"document.t_vars" +","
                   +"document.t_params"
                   +");";
		return text;
	}-*/;
}
