package com.manning.gwtia.ch09.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * The EntryPoint for the examples.
 * 
 * It handles the History in case a user comes in with a URL showing a history
 * token, plus initialises the ExamplePanel where the examples will be shown.
 * 
 * This is just a convenient way to display the frame for our examples; for the
 * examples code, you should look in the relevant package - in this case
 * 
 * ch09.client.computestyle for the ComputeStyle example ch09.client.jsoverlay
 * for the JavaScript Overlay example ch09.client.geolocate for the GeoLocation
 * example ch09.client.jsonp for the JSONP example
 * 
 */
public class Examples implements EntryPoint, ValueChangeHandler<String> {

	CompanyView examples = new CompanyView();

	public void onModuleLoad() {
		RootPanel.get().add(examples, 0, 0);
		setUpHistoryManagement();
	}

	public void setUpHistoryManagement() {
		// Make this class your history manager (see onValueChange method)
		History.addValueChangeHandler(this);
		// Handle any existing history token
		History.fireCurrentHistoryState();
	}

	public void onValueChange(ValueChangeEvent<String> event) {
		// Get the token from the event
		String page = event.getValue().trim();
		// Check if the token is null or empty
		if ((page == null) || (page.equals("")))
			examples.showIntro();
		// Else check what the token is and call the appropriate method.
		else if (page.equals(HistoryTokens.EMPLOYEES))
			examples.showEmployees();
		else if (page.equals(HistoryTokens.PHONEBOOK))
			examples.showPhoneBook();
		else if (page.equals(HistoryTokens.CONTACTS))
			examples.showContacts();
		else if (page.equals(HistoryTokens.LIST_1))
			examples.showList1();
		else if (page.equals(HistoryTokens.LIST_2))
			examples.showList2();
	}

}
