package com.manning.gwtia.ch11.client.photowall;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch11.client.photowall.event.InitializeHandler;
import com.manning.gwtia.ch11.client.photowall.event.ItemSelectedHandler;
import com.manning.gwtia.ch11.client.photowall.jsoverlay.Item;
import com.google.gwt.uibinder.client.UiHandler;


public class PhotoWallExample extends Composite {
	
	// UiBinder template
	interface PhotoWallExampleUiBinder extends UiBinder<Widget, PhotoWallExample> {}
	private static PhotoWallExampleUiBinder uiBinder = GWT.create(PhotoWallExampleUiBinder.class);
	
	@UiField PhotoWallWidget theWall;
	@UiField ListBox feeds;
	
	private class FeedData{
		private String displayName;
		private String feedName;
		
		public String getDisplayName(){return displayName;}
		public String getFeedName(){return feedName;}
		
		public FeedData(String dN, String fN){
			displayName = dN;
			feedName = fN;
		}
	}
	
	// Some feed data - display name and the url.
	FeedData FLICKR_ALL = new FeedData("Flickr - all", "api://www.flickr.com/");
	FeedData FLICKR_STHL = new FeedData("Flickr - Stockholm", "api://www.flickr.com/?search=stockholm");
	FeedData FLICKR_BOOK = new FeedData("Flickr - Book", "api://www.flickr.com/?user=ajt");
	FeedData PICC_ALL = new FeedData("Picasa - all", "api://picasaweb.google.com/");
	FeedData PICC_STHL = new FeedData("Picasa - Stockholm", "api://picasaweb.google.com/?search=stockholm");
	
	
	public PhotoWallExample() {
		initWidget(uiBinder.createAndBindUi(this));
		this.setSize("100%", "100%");
		setUpGui();
		setUpHandlers();
	}
	
	
	/**
	 * Add some feeds and disable the drop down box
	 * (we'll re-enable it again if the 3rd party library loads)
	 */
	private void setUpGui(){
		feeds.addItem(FLICKR_ALL.getDisplayName());
		feeds.addItem(FLICKR_STHL.getDisplayName());
		feeds.addItem(FLICKR_BOOK.getDisplayName());
		feeds.addItem(PICC_ALL.getDisplayName());
		feeds.addItem(PICC_STHL.getDisplayName());
		
		// Set the feed selection to disabled
		feeds.setEnabled(false);
	}
	
	private void setUpHandlers(){
		// Add an Initialise Handler that enables the feeds if the library has loaded.
		theWall.addInitializeHandler(new InitializeHandler(){
			@Override
			public void onInitialized() {
				feeds.setEnabled(true);
			}
		});
		
		theWall.addItemSelectedHandler(new ItemSelectedHandler(){

			@Override
			public void onItemSelected(Item item) {
				Window.alert("Selected's title: "+item.getTitle());				
			}
		});
	}
	

	/**
	 * Handle a change in the drop down box of feeds
	 * @param evt
	 */
	@UiHandler("feeds")
	public void onSelect(ChangeEvent evt){
		String val = feeds.getItemText(feeds.getSelectedIndex());
		if (val.equals(FLICKR_ALL.getDisplayName())){
			theWall.setFeed(FLICKR_ALL.getFeedName());
		} else if (val.equals(FLICKR_STHL.getDisplayName())){
			theWall.setFeed(FLICKR_STHL.getFeedName());
		} else if (val.equals(PICC_ALL.getDisplayName())){
			theWall.setFeed(PICC_ALL.getFeedName());
		} else if (val.equals(PICC_STHL.getDisplayName())){
			theWall.setFeed(PICC_STHL.getFeedName());
		}else if (val.equals(FLICKR_BOOK.getDisplayName())){
			theWall.setFeed(FLICKR_BOOK.getFeedName());
		} 
	}
}
