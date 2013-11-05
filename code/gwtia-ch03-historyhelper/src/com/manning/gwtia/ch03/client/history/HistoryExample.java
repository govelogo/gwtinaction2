/**
 * 
 */
package com.manning.gwtia.ch03.client.history;

// History is triggered by ValueChangeEvents of the URL, and need to be handled by a ValueChangeHandler
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
// Importing the History class.
import com.google.gwt.user.client.History;
// We saw Window in earlier examples where it was used to display alerts to the user....
import com.google.gwt.user.client.Window;
// ...in this case, we will be using it to also handle closing events, to catch a user that might be accidentally
// leaving our application.
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;

// The rest of these imports just relate to standard user interface aspects.
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;

/**
 * This example shows how to manage history in a GWT application.
 * 
 * The example shows three buttons, which when pressed change the history token.
 * When a history token is changed, it is detected and displayed on the screen....
 * ...so if the user hits forward and/or back buttons, the token changes can be traced.
 * 
 * If the user tries to leave the example by clicking "back" when there are no more tokens, or clicking on the 
 * browser's close button, then they are warned about this and have the option to prevent the closing.
 *  
 * The class implements the ValueChangeHandler<String> interface as that is required part of History handling.
 * (Note that we also have history handling on the Examples.java class as part of the example framework - the
 * two handlers do not interfere as we are using distinct tokens)
 */
public class HistoryExample extends Composite implements ValueChangeHandler<String>{

	// The tokens that are used in this example
	final String CONTACT = "Contact";
	final String HOME = "Home";
	final String PRODUCTS = "Products";
	final String BACK = "History Back";
	final String FORWARD = "History Forward";

	
	/**
	 * Panel that will hold the display where we will show the tokens.
	 */
	ScrollPanel tokenDisplayHolder;

	/**
	 * Where the text of the tokens will be displayed.
	 */
	FlowPanel tokenDisplay;

	// Buttons used for changing history token.
	Button home;
	Button products;
	Button contact;
	
	Button back;
	Button forward;


	/**
	 * Set up the user interface.
	 * We use old school style here, but from chapter 6 onwards we will switch to declarative
	 * approach using UiBinder.
	 * @return
	 */
	private FlowPanel setUpGui() {
		// Create a FlowPanel to hold three buttons
		FlowPanel toolbar = new FlowPanel();
		FlowPanel histories = new FlowPanel();
		// Create the three buttons
		home = new Button(HOME);
		products = new Button(PRODUCTS);
		contact = new Button(CONTACT);
		// Add the three buttons to the FlowPanel
		histories.add(home);
		histories.add(products);
		histories.add(contact);
		
		FlowPanel directions = new FlowPanel();
		back = new Button(BACK);
		forward = new Button(FORWARD);
		directions.add(back);
		directions.add(forward);
		
		toolbar.add(histories);
		toolbar.add(directions);
		
		// Create the FlowPanel where token text will be displayed.
		tokenDisplay = new FlowPanel();
		// Create the ScrollPanel that will hold the above FlowPanel
		tokenDisplayHolder = new ScrollPanel();
		// Set the size of the panels to be 100% (if we don't do this for the ScrollPanel, then nothing
		// will be displayed.
		toolbar.add(tokenDisplayHolder);
		tokenDisplay.setSize("100%", "100%");
		tokenDisplay.getElement().getStyle().setBackgroundColor("#44ffff");
		tokenDisplayHolder.setSize("200px", (Window.getClientHeight()-50)+"px");
		// Add the FlowPanel that will show the tokens to the ScrollPanel
		tokenDisplayHolder.add(tokenDisplay);
		return toolbar;
	}
	
	/**
	 * Sets up the widget event handling.
	 * You don't have to have a separate method to do this, event handlers can be set up
	 * when widgets are created, for example; we have done it this way in this example, simply
	 * to have the event handling set-up in one place for you to see.
	 */
	private void setUpEventHandling(){
		// Handle clicks on the home button by setting the history token to the HOME value
		home.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				// History token is simply set by calling the newItem method.
				History.newItem(HOME);
			}
		});

		// Handle clicks on the products button by setting the history token to the PRODUCTS value
		products.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				History.newItem(PRODUCTS);
			}
		});
		
		// Handle clicks on the contact button by setting the history token to the CONTACT value
		contact.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				History.newItem(CONTACT);
			}
		});
		
		back.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				History.back();
			}
		});
		
		forward.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				History.forward();
			}
		});
	}
	
	/**
	 * Set up the History handling, and Window closing handlers.
	 * As with widget events, there is no requirement to have a separate method to set these up, we do it
	 * in this example purely to keep it all in one place.
	 * 
	 * This method adds the ValueChangeHandler to the History object so that history is handled (the handler 
	 * is this class).  It then calls fireCurrentHistoryState method to see (and handle) any token that might 
	 * already be present (if, for example, the user comes here directly via a URL call to 
	 * http://......Example.html#Contact ).
	 * 
	 * Finally, this method adds a WindowClosing handler.  Whilst strictly not part of History management, 
	 * this handler will allow us to trap when a user tries to close the browser window (or if they click 
	 * the back button to bounce out of the application when the history stack has become empty) and warn 
	 * them.  The user can then cancel the navigation if they so wish.
	 * 
	 */
	public void setUpHistoryManagement(){
		// Make this class your history manager (see onValueChange method)
		History.addValueChangeHandler(this);
		// Handle any existing history token
		History.fireCurrentHistoryState();
		// Trap the user if they hit the back button too many times
		Window.addWindowClosingHandler(new ClosingHandler(){
			public void onWindowClosing(ClosingEvent event) {
				event.setMessage("Ran out of history.  Now leaving application, is that OK?");
			}
		});
	}

	/**
	 * This is the function that handles history change events.
	 * 
	 * When the history token is changed in the URL, GWT fires a
	 * ValueChangeEvent that is handled in this method (since we called
	 * History.addValueChangeHandler(this) in the onModuleLoad method).
	 * 
	 * The history token is the part of the URL that follows the hash symbol.
	 * For example http://www.someurl.se/MyApp.html#home has the token "home".
	 * 
	 * In this example we could just simply display the value of the token retrieved
	 * from the event; but we want to show how to check the value of the token and 
	 * how to take a different action depending on that token (you can also map this
	 * code to the more complicated example seen in the main Chapter 3 example)
	 * 
	 */
	public void onValueChange(ValueChangeEvent<String> event) {
		// Get the token from the event
		String token = null;
		if (event.getValue()!=null) token = event.getValue().trim();
		// Check if the token is null or empty
		if ((token == null) || (token.equals("")))
			showHomePage();
		// Else check what the token is and call the appropriate method.
		else if (token.equals(PRODUCTS))
			showProducts();
		else if (token.equals(CONTACT))
			showContact();
		else
			// By default, show the Home page.
			showHomePage();
	}


	/**
	 * Show the contact page - i.e. place a new label on the current screen
	 */
	private void showContact() {
		showToken(CONTACT);
	}

	/**
	 * Show the home page - i.e. place a new label on the current screen
	 */
	private void showHomePage() {
		showToken(HOME);
	}

	/**
	 * Show the products page - i.e. place a new label on the current screen
	 */
	private void showProducts() {
		showToken(PRODUCTS);
	}
	
	
	/**
	 * Simple helper method to show a string on the screen
	 * @param token
	 */
	private void showToken(String token){
		if(tokenDisplay!=null){
			Label newItem = new Label(token);
			tokenDisplay.add(newItem);
			tokenDisplayHolder.ensureVisible(newItem);
		}
	}

	/**
	 * Creates the Widget.
	 * Sets up history handling, the GUI components, and button handling.
	 */
	public HistoryExample(){
		// Set up history management
		setUpHistoryManagement();
		// Create the user interface
		initWidget(setUpGui());
		// Set up event handling on the buttons
		setUpEventHandling();
	}
}
