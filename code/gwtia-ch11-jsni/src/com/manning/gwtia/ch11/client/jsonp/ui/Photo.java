/**
 * 
 */
package com.manning.gwtia.ch11.client.jsonp.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 *  Simple widget to show a photo, title and description
 *
 */
public class Photo extends Composite{

	interface PhotoUiBinder extends UiBinder<Widget, Photo> {}
	private static PhotoUiBinder uiBinder = GWT.create(PhotoUiBinder.class);

	@UiField Label title;
	@UiField Image photo;
	@UiField Label description;
	
	public @UiConstructor Photo(String theTitle, String theDescription, String src){
		initWidget(uiBinder.createAndBindUi(this));
		this.setSize("100%", "100%");
		photo.setUrl(src);
		title.setText(theTitle);
		description.setText(theDescription);
	}	
}
