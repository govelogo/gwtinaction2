/**
 * 
 */
package com.manning.gwtia.ch11.client.jsonp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch11.client.jsonp.jsoverlays.Entry;
import com.manning.gwtia.ch11.client.jsonp.jsoverlays.Feed;
import com.manning.gwtia.ch11.client.jsonp.ui.Photo;

/**
 * 
 * This example retrieves a list of photos from Picasso as a JSONP object and uses
 * JavaScript overlays to access information and display them on screen.
 *
 */
public class JSONPExample extends Composite{

	// UiBinder template code
	interface JSONPExampleUiBinder extends UiBinder<Widget, JSONPExample> {}
	private static JSONPExampleUiBinder uiBinder = GWT.create(JSONPExampleUiBinder.class);
	
	// Panel where we will show the photos
	@UiField FlowPanel photoArea;
		
	/**
	 * Initialise the widget
	 */
	public JSONPExample(){
		initWidget(uiBinder.createAndBindUi(this));
		this.setSize("100%", "100%");
	}
	
	// The maximum number of results to retrieve.
	final int MAX_RESULTS = 12;
	
	
	/**
	 * Uses the GWT JsonpRequestBuilder to retrieve a JSONP object detailing photos on Picasso
	 * 
	 * Then it uses JavaScript overlays to access the photos and request they are presented on the
	 * browser screen.
	 * 
	 */
	private void getData(){
		// The URL to access
		String url = "https://picasaweb.google.com/data/feed/api/all?max-results="+MAX_RESULTS+"&alt=json-in-script";
		
		// Build a JsonpRequestBuilder object
		JsonpRequestBuilder req = new JsonpRequestBuilder();
		// Set timeout of request to be 3 seconds
		req.setTimeout(3000);
		// Make request
		req.requestObject(url, new AsyncCallback<Feed>(){
			
			// Handle a failure
			public void onFailure(Throwable caught) {
				Window.alert("Error: " + caught);
			}

			// Handle success
			public void onSuccess(Feed result) {
				displayPhotos(result.getEntries());
			}			
		});
	}
	
	/**
	 * Loop through a bunch of entries and create a Photo widget and add to the display
	 * @param photos An array of photos
	 */
	private void displayPhotos(JsArray<Entry> photos){
		// Loop through the array
		for (int loop=0; loop<photos.length(); loop++){
			// Get the photo details
			Entry photoDetails = photos.get(loop);
			// Create a new Photo widget with appropriate information
			Photo photo = new Photo("Title: "+photoDetails.getTitle(), 
									"",
									manageUrl(photoDetails.getContent().getSrc()));
			photo.setSize("300px", "200px");
			photo.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			// Add to the screen
			photoArea.add(photo);
		}
		
	}
	
	/**
	 * Picasa has started returning "https" as part of photo URLs, which we can't show within our 
	 * IFRAME managed GWT application.
	 * However, just using http works fine to get the image, so we'll check and replace if needed.
	 * @param url
	 * @return
	 */
	private String manageUrl(String url){
		GWT.log(url);
		String result = url;
		if (result.startsWith("https"))
			result = "http"+result.substring(5);
		GWT.log(result);
		return result;
	}
	
	
	// When the Get Photos button is clicked, get the photos.
	@UiHandler("getPhotos")
    void getPhotos(ClickEvent event){
		photoArea.clear();
		//photoArea.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
		getData();
    }	
	
	
}
