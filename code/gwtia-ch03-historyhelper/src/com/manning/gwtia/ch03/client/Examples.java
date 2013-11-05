/** 
**/
package com.manning.gwtia.ch03.client;

import com.google.gwt.core.client.EntryPoint;
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
public class Examples implements EntryPoint
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
    }    
}
