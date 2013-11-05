package com.manning.gwtia.ch11.client.photowall;

import com.google.gwt.core.client.JavaScriptObject;
import com.manning.gwtia.ch11.client.photowall.event.InitializeHandler;
import com.manning.gwtia.ch11.client.photowall.event.ItemSelectedHandler;

public class Cooliris extends JavaScriptObject{

	protected Cooliris(){}

	
	public native static Cooliris get()/*-{
		return $wnd.cooliris;
	}-*/;
	
	public final native void addHandlers(InitializeHandler handler, ItemSelectedHandler handler2)/*-{
		$wnd.onItemSelected = function(item){
			if (item==null){}else{
				handler2.@com.manning.gwtia.ch11.client.photowall.event.ItemSelectedHandler::onItemSelected(Lcom/manning/gwtia/ch11/client/photowall/jsoverlay/Item;)(item);
			}
		}
		this.onEmbedInitialized = function(){
			$wnd.cooliris.embed.setCallbacks({
                  select: $wnd.onItemSelected
        	});        
        	handler.@com.manning.gwtia.ch11.client.photowall.event.InitializeHandler::onInitialized()();
		}
	}-*/;
	
	public final native void setFeed(String feed)/*-{
		this.embed.setFeedURL(feed);
	}-*/;
}
