/**
 * 
 */
package com.manning.gwtia.ch03.client;

/*
 * The import list is longer in this example than the HelloWorld in Ch2.
 * This is simply because we do more things
 */
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SimpleHtmlSanitizer;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * This is the code for GWT In Action's Chapter 3 main example.
 * It demonstrates the use of a number of panels and widgets, 
 * and a number of ways of adding widgets to the application.
 * 
 * There are 3 "pages" - home, products and contact that you 
 * can move between and are also navigatable using the browser's
 * forward and back buttons.
 * 
 * A WindowClosing handler is also added in the history handling to 
 * inform you if you have hit the back button so much that you 
 * are about to leave the application.
 *
 */
public class BasicProject implements EntryPoint, ValueChangeHandler<String> {

	/**
	 * Numerical values to reference the tabs the content pages are held in.
	 */
	static final int DECK_HOME = 0;
	static final int DECK_PRODUCTS = 1;
	static final int DECK_CONTACT = 2;
	
	/**
	 * Strings representing the history tokens we will use to indicate which tab content
	 * the user is viewing.
	 */
	static final String TOKEN_HOME = "Home";
	static final String TOKEN_PRODUCTS = "Products";
	static final String TOKEN_CONTACT = "Contact";
	
	/**
	 * The filename of our logo image
	 */
	private static final String LOGO_IMAGE_NAME = "gwtia.png";
	
	/**
	 * A popup panel that will be displayed if the search button is selected. 
	 */
	 PopupPanel searchRequest;
	
	/**
	 * An enumeration covering the 3 pages that will be involved in the application
	 * and the history.
	 * 
	 * Each enumerated item contain 2 pieces of information: a number that
	 * relates to the deck card number, and a String that relates to the history token.
	 * 
	 * This is just our way of associating history token to tab panel index
	 * 
	 */
	enum Pages {
		HOME(DECK_HOME, TOKEN_HOME), PRODUCTS(DECK_PRODUCTS, TOKEN_PRODUCTS), CONTACT(DECK_CONTACT, TOKEN_CONTACT);
		
		// Holds the card number in the deck this enumeration relates to. 
		private int val;
		// Holds the history token value this enumeration relates to.
		private String text;
		
		// Simple method to get the card number in the deck this enumeration relates to.
		int getVal(){return val;}
		// Simple method to get the history token this enumeration relates to.
		String getText(){return text;}

		// Enumeration constructor that stores the card number and history token for this enumeration.
		Pages(int val, String text) {
			this.val = val;
			this.text = text;
		};
	}

	/**
	 * Returns the HTML content of an existing DOM element on the HTML page.
	 * 
	 * Should be careful with these type of methods if you are going to use the data
	 * later to ensure people are not injecting scripts into your code.
	 * In our example, we control the HTML that the data is retrieved from.
	 * 
	 * @param id The id of the DOM element we wish to get the content for.
	 * @return The HTML content of the DOM element.
	 */
	private String getContent(String id) {
		// Initialise the return string.
		String toReturn = "";
		
		// Find the DOM element by the id passed in.
		Element element = DOM.getElementById(id);
		
		// Make sure we've found the DOM element and then manipulate it.
		if (element!=null){
		
			// Get the inner HTML content of the DOM element.
			toReturn = DOM.getInnerHTML(element);

		
			// Set the inner value of the DOM element to an empty string
			// if we don't do this, then it is still displayed on the screen.
			DOM.setInnerText(element, "");
		
			// Comment the following two lines of code out to not use SafeHTML to create the response.
			// If we use it, then this makes sure the HTML we have from the HTML page is sanitized against 
			// any XSS attacks.  In this example's case, the hyperlink in contacts page is sanitized, i.e.
			// you cannot click on it.  
			// This can be seen as overkill in this case, but security should always be at the heart of
			// your development (it is too large a topic for us to cover within the GWT in Action book).
			SafeHtml sfHtml = SimpleHtmlSanitizer.sanitizeHtml(toReturn);
			toReturn = sfHtml.asString();
		} else {
			// If we can't find the content then let's just put an error message in the content
			// (You can test this by changing the id of the DOM elements in the HTML page - you probably need
			// to clear your browser's cache to see the impact of any changes you make).
			toReturn = "Unable to find "+id+" content in HTML page";
		}
		return toReturn;
	}

	/**
	 * This TabLayoutPanel will hold the application's 3 "pages" of content.
	 */
	TabLayoutPanel content;
	
	/**
	 * This button  will wrap the existing HTML button defined in the HTML page and 
	 * is used for the dummy search capability.
	 */
	Button search;
	
	/**
	 * This panel  sits on the right hand side of the page to allow user feedback.
	 * It will slide in when the mouse is over it and slides back out again if the 
	 * mouse moves off it.
	 */
	FocusPanel feedback;
	
	/**
	 * The image logo.
	 */
	Image logo;
	
	/**
	 * Here we set up the logo by creating a new Image widget, and prevent the 
	 * default browser action from occuring on it.
	 */
	private void insertLogo(){
		// Create the logo image and prevent being able to drag it to browser location bar
		// by overriding its onBrowserEvent method.
		logo = new Image(GWT.getModuleBaseURL() + "../" + LOGO_IMAGE_NAME){
			public void onBrowserEvent(Event evt){
				// Comment out the next line to be able to drag logo to the browser location
				// bar; leave it in to prevent the default browser action.
				evt.preventDefault();
				
				// Play nice with the event system by bubbling the event upwards
				super.onBrowserEvent(evt);
			}
		};
	}
	
	/**
	 * Wrap the search button that already exists on the HTML page and store it as the
	 * previously declared search Button widget.  I the button doesn't exist (you could, 
	 * for example, edit the HTML page to take it away or change its id value to something
	 * else) then we'll log that fact and create it to avoid null pointer exceptions 
	 * when accessing the button elsewhere in the application (like when adding event 
	 * handlers to it). 
	 */
	private void wrapExisitngSearchButton(){
		// Try and find the DOM element
		Element el = DOM.getElementById("search");
		
		// If the element is not null, then we've found it, so let's wrap it
		if(el!=null){
			search = Button.wrap(el);
		} else {
			// The search button is missing in the underlying HTML page, so we can't wrap it...
			// Let's log the fact it is missing - in development mode this will appear 
			// in the console, in web mode the code will be compiled out
			GWT.log("The search button is missing in the underlying HTML page, so we can't wrap it...trying to create it instead");
			// We should play safe and create it manually and throw it on the page somewhere - otherwise we risk having 
			// null pointer exceptions elsewhere in our application as the button doesn't exist yet.
			search = new Button("search");
			RootPanel.get().add(search);
		}
	}

	/**
	 * Here we set up the event handling that we will drive user interaction.
	 * 
	 * 1.  A SelectionHandler for when a new tab is selected.
	 * 2.  A ClickHandler for if the search button is clicked.
	 * 3.  Some Mouse handlers and ClickHandler if the feedback tab is interacted with.
	 * 
	 * You don't have to follow this style of programming and put all your event handling code
	 * into one method, we do it here as it makes sense and helps us examine particular aspects
	 * of code in one place (however, by doing it this way instead of, for example adding handlers
	 * directly after defining widgets, means we should check each widget is not null before 
	 * adding the handler - we won't as by inspection we know all widgets are instantiated elsewhere 
	 * before this method is called; but you should be aware of these type of dependencies in your
	 * own code). 
	 * 
	 */
	private void setUpEventHandling(){
		
		/**
		 *  If a tab is selected then we want to add a new history item to the History object.
		 *  (this effectively changes the token in the URL, which is detected and handled by 
		 *  GWT's History sub-system.
		 */
		content.addSelectionHandler(new SelectionHandler<Integer>(){
			public void onSelection(SelectionEvent<Integer> event) {
				// Determine the tab that has been selected by interrogating the event object.
				Integer tabSelected = event.getSelectedItem();
				
				// Create a new history item for this tab (using data retrieved from Pages enumeration)
				History.newItem(Pages.values()[tabSelected].getText());
			}
		});
		
		
		/**
		 *  If the search button is clicked, we want to display a little pop-up panel which allows
		 *  the user to type in a search term.  The TextBox where the user types search terms should 
		 *  automatically gain focus to make it more user friendly.
		 */ 
		search.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				FlowPanel qAnswer;
				final TextBox searchTerm = new TextBox();
				
				// If search button is clicked for the first time then the searchRequest Pop-up panel does not yet exist
				// so we'll build it first as follows:
				if (searchRequest==null){
					// Create the PopupPanel widget
					searchRequest = new PopupPanel();
					
					// Create a FlowPanel to hold the question and answer for the search term
					qAnswer = new FlowPanel();
					// Add a Label to the Flow Panel that represents the "Search For" text
					qAnswer.add(new Label("Search For:"));
					// Add the answer TextBox (which we declared above) to the FlowPanel
					qAnswer.add(searchTerm);
					
					// Add a change handler to the TextBox so that when there is a change to search term 
					// we would "start" the search (we don't implement the search capability in this simple example)
					searchTerm.addChangeHandler(new ChangeHandler(){
						public void onChange(ChangeEvent event) {
							// Hide the popup panel from the screen
							searchRequest.hide();
							// "start" the search
							Window.alert("If implemented, now we would search for: "+searchTerm.getText());
						}
					});

					// Add the question/answer to the search pop-up.
					searchRequest.add(qAnswer);
					
					// Now we'll set some properties on the pop up panel, we'll:
					// * indicate that the popup should be animated
					// * show it relative to the search button widget 
					// * close it if the user clicks outside of it popup panel, or if the history token is changed
					searchRequest.setAnimationEnabled(true);
					searchRequest.showRelativeTo(search);
					searchRequest.setAutoHideEnabled(true);
					searchRequest.setAutoHideOnHistoryEventsEnabled(true);
				} else {
					// search popup already exists, so clear the TextBox contents...
					searchTerm.setText("");
					// ... and simply show it.
					searchRequest.show();
				}
				
				// Set the TextBox of the popup Panel to have focus - this means that once the pop up is displayed
				// then any keypresses the user makes will appear directly inthe TextBox.  If we didn't do this, then 
				// who knows where the text would appear.
				searchTerm.setFocus(true);
			}			
		});
		
		/**
		 * If the user moves mouse over feedback tab, change its style 
		 * (increases its size and changes colour - styles are in BasicProject.css)
		 */
		feedback.addMouseOverHandler(new MouseOverHandler(){
			public void onMouseOver(MouseOverEvent event) {
				// Remove existing normal style
				feedback.removeStyleName("normal");
				// Add the active style
				feedback.addStyleName("active");
				// Set overflow of whole HTML page to hidden  to minimise display of scroll bars.
				RootPanel.getBodyElement().getStyle().setProperty("overflow", "hidden");
			}
		});
		
		/**
		 * If use moves mouse out of the feedback panel, return its style to normal
		 * (decreases its size and changes colour - styles are in BasicProject.css)
		 */
		feedback.addMouseOutHandler(new MouseOutHandler(){
			public void onMouseOut(MouseOutEvent event) {
				feedback.removeStyleName("active");
				feedback.addStyleName("normal");
				RootPanel.getBodyElement().getStyle().setProperty("overflow", "auto");
			}
		});
		
		/**
		 * If user clicks on the feedback tab we should start some feedback functionality.
		 * In this example, it simply displays an alert to the user.
		 */
		feedback.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				Window.alert("You could provide feedback if this was implemented");
			}
		});
	}
	
	
	/**
	 * The HTMLPanels that will hold the content we want to display in the TabPanel.
	 */
	HTMLPanel homePanel;
	HTMLPanel productsPanel;
	HTMLPanel contactPanel;

	/**
	 * We'll build the tab panel's content from the HTML that is already in the HTML
	 * page.
	 */
	private void buildTabContent(){
		// Create the main content widget
		// First retrieve the existing content for the pages from the HTML page
		homePanel = new HTMLPanel(getContent(Pages.HOME.getText()));
		productsPanel = new HTMLPanel(getContent(Pages.PRODUCTS.getText()));
		contactPanel = new HTMLPanel(getContent(Pages.CONTACT.getText()));
		
		// set the style of HTMLPanels
		homePanel.addStyleName("htmlPanel");
		productsPanel.addStyleName("htmlPanel");
		contactPanel.addStyleName("htmlPanel");
		
		// Create the tab panel widget
		content = new TabLayoutPanel(20, Unit.PX);

		// Add the content we have just created to the tab panel widget
		content.add(homePanel, Pages.HOME.getText());
		content.add(productsPanel, Pages.PRODUCTS.getText());
		content.add(contactPanel, Pages.CONTACT.getText());
		
		// Indicate that we should show the HOME tab initially.
		content.selectTab(DECK_HOME);
	}

	
	/**
	 * Creating the Feedback tab 
	 */
	private void createFeedbackTab(){
		// Create the FeedBack tab
		feedback = new FocusPanel();
		feedback.setStyleName("feedback");
		feedback.addStyleName("normal");
		// Create VerticalPanel that holds two labels "feed" and "back"
		VerticalPanel text = new VerticalPanel();
		text.add(new Label("Feed"));
		text.add(new Label("Back"));
		feedback.add(text);
	}

	
	/**
	 * Style the tab panel using methods in the UIObject class.
	 */
	private void styleTabPanelUsingUIObject(){
		// Set up the heights of the pages.
		homePanel.setHeight("400px");
		productsPanel.setHeight("400px");
		contactPanel.setHeight("400px");
		content.setHeight("420px");
	}
	
	/**
	 * Style the search button using DOM methods available through the Widget.getElement().getStyle() method.
	 */
	private void styleButtonUsingDOM(){
		// Set up some styling on the button
		search.getElement().getStyle().setProperty("backgroundColor", "#ff0000");
		search.getElement().getStyle().setProperty("border", "2px solid");
		search.getElement().getStyle().setOpacity(0.7);
	}
	

	/**
	 * Sets up the GUI components used in the application
	 * 
	 * 1. A TabPanel that holds "page" content from the original HTML page
	 * 2. A search button that is from the original HTML page
	 * 3. An image for the logo
	 * 4. A feedback bar.
	 * 
	 */
	private void setUpGui() {
		// Build the TabPanel content from existing HTML page text
		buildTabContent();
		// Wrap the existing search button
		wrapExisitngSearchButton();
		// Insert a logo into a defined slot in the HTML page
		insertLogo();
		//Create the Feedback tab on the right of the page
		createFeedbackTab();
		
		// Style the TabPanel using methods from the UIObject it inherits
		styleTabPanelUsingUIObject();
		// Style the Button using low level DOM access
		styleButtonUsingDOM();
		
		// Add the feedback panel directly to the page
		RootPanel.get().add(feedback);
		// Add the logo to the DOM element with id of "logo"
		RootPanel logoSlot = RootPanel.get("logo");
		if (logoSlot!=null)logoSlot.add(logo);
		// Add the TabPanel to the DOM element with the id of "content"
		RootPanel contentSlot = RootPanel.get("content");
		if (contentSlot!=null) contentSlot.add(content);
		// There's no need to add the button, as it is already in the original HTML page.
	}

	/**
	 * This is the entry point method which will create the GUI and set up the History handling.
	 */
	public void onModuleLoad() {
		// Create the user interface
		setUpGui();		
		// Set up history management
		setUpHistoryManagement();
		// Set up all the event handling required for the application.
		setUpEventHandling();
	}

	/**
	 * Set up the History management for the application.
	 */
	public void setUpHistoryManagement(){
		// Make this class your history manager (see onValueChange method)
		History.addValueChangeHandler(this);
		// Handle any existing history token
		History.fireCurrentHistoryState();
		// Trap user hitting back button too many times.
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
	 */
	public void onValueChange(ValueChangeEvent<String> event) {
		// Get the token from the event
		String page = event.getValue().trim();
		// Check if the token is null or empty
		if ((page == null) || (page.equals("")))
			showHomePage();
		// Else check what the token is and cal the appropriate method.
		else if (page.equals(Pages.PRODUCTS.getText()))
			showProducts();
		else if (page.equals(Pages.CONTACT.getText()))
			showContact();
		else
			showHomePage();
	}
	

	/**
	 * Show the contact page - i.e. place a new label on the current screen
	 */
	private void showContact() {
		content.selectTab(Pages.CONTACT.getVal());
	}

	/**
	 * Show the home page - i.e. place a new label on the current screen
	 */
	private void showHomePage() {
		content.selectTab(Pages.HOME.getVal());
	}

	/**
	 * Show the products page - i.e. place a new label on the current screen
	 */
	private void showProducts() {
		content.selectTab(Pages.PRODUCTS.getVal());
	}
}
