/**
 */
package com.manning.gwtia.ch13.client.direction;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * An example that allows exploration of GWT's Direction on widgets.
 * User can choose between a RTL and LTR locale and the widget component parts automatically display in the 
 * correct direction (though as the text is English, it always displays LTR.
 *
 */
public class DirectionExample extends Composite{

	// UiBinder template code
    interface DirectionExampleUiBinder extends UiBinder<Widget, DirectionExample> {}
    private static DirectionExampleUiBinder uiBinder = GWT.create(DirectionExampleUiBinder.class);
    
    @UiField Label dirNotification;
    @UiField DataField ourQuestion;
    @UiField (provided=true) ListBox language;

    // Two locales, one is LTR the other RTL
    private final String LTR_locale = "en";
    private final String RTL_locale = "ar";
    
    // Build the UI
    public DirectionExample(){
    	language = new ListBox();
    	// Add 2 choices to the drop down
    	language.addItem("Left-to-right language");
    	language.addItem("Right-to-Left language");
    	// Set the drop down to show the text representing the selected locale on start-up
    	if (LocaleInfo.getCurrentLocale().isRTL()) language.setItemSelected(1, true); else language.setItemSelected(0, true);
    	
    	initWidget(uiBinder.createAndBindUi(this));
    	this.setSize("100%", "100%");


    	// Set text in the Question/Answer widget
    	// Needs to be done after uiBinder.createAndBindUi so that the objects are not null.
    	ourQuestion.setQuestion("The Question");
    	ourQuestion.setAnswer("The Answer");
    	
    	// Indicate whether direction is RTL or not on the UI
    	dirNotification.setText(""+LocaleInfo.getCurrentLocale().isRTL());
    }
    
    /**
     * Handle update to the locale drop down by changing the locale in the URL and reloading the application.
     */
    @UiHandler("language")
    void changeLocale (ChangeEvent event){
		String locale = language.getSelectedIndex() == 0 ? LTR_locale : RTL_locale;
		UrlBuilder newUrl = Window.Location.createUrlBuilder();
		newUrl.setParameter("locale", locale);
		Window.Location.assign(newUrl.buildString());
    }
}
