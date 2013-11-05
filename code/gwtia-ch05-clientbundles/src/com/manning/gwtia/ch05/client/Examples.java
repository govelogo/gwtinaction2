/** 
**/
package com.manning.gwtia.ch05.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * The EntryPoint for the examples.
 * 
 * It handles the History in case a user comes in with a URL showing a history token, plus
 * initialises the ExamplePanel where the examples will be shown.
 * 
 * This is just a convenient way to display the frame for our examples; for the 
 * examples code, you should look in the relevant package - in this case
 * 
 * ch03.client.history for the History example
 * ch03.client.layout for the Layout example
 *  
 */
public class Examples implements EntryPoint, ValueChangeHandler<String>
{

	
	ExamplePanel examples = new ExamplePanel();
	
	/**
	 * Entry Point. Add an ExamplePanel to the RootPanel, and handle any history token that might
	 * have been present when application started (this might lead to starting an example immediately, 
	 * or leaving the introduction screen displayed).
	 */
    public void onModuleLoad ()
    {
        RootPanel.get().add(examples, 0, 0);
    	setUpHistoryManagement();
    }
    

    /**
     * Register this class as handling history events, and check to see if we already have to do that.
     */
	public void setUpHistoryManagement(){
		// Make this class your history manager (see onValueChange method)
		History.addValueChangeHandler(this);
		// Handle any existing history token
		History.fireCurrentHistoryState();
	}
	

	/**
	 * Handle any change to the history.
	 * 
	 * If there is a change to the history, then a ValueChangeEvent is fired.  This method handles that
	 * by parsing the found token, and based on the value, it can request the examplePanel shows the
	 * History example, Layout example, or the introduction screen.
	 * 
	 */
	public void onValueChange(ValueChangeEvent<String> event) {
		// Get the token from the event
		String page = event.getValue().trim();
		// Check if the token is null or empty
		if ((page == null) || (page.equals(""))){}
			//showHomePage();
		// Else check what the token is and call the appropriate method.
		else if (page.equals(HistoryTokens.DATA))
			examples.showData();
		else if (page.equals(HistoryTokens.TEXT))
			examples.showText();
		else if (page.equals(HistoryTokens.IMAGE))
			examples.showImage();		
		else if (page.equals(HistoryTokens.CSS))
				examples.showCss();
		else
			examples.showInstructionsPanel();
	}    
}
