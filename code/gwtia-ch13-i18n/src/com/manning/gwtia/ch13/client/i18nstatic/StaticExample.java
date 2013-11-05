/**
 */
package com.manning.gwtia.ch13.client.i18nstatic;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch13.client.i18nstatic.clientbundle.LocaleFlags;
import com.manning.gwtia.ch13.client.i18nstatic.constants.CountryDetails;
import com.manning.gwtia.ch13.client.i18nstatic.messages.ExampleMessages;
import com.manning.gwtia.ch13.client.i18nstatic.messages.ExampleMessages.Gender;

/**
 * This is an example of using Static internationalisation (i18n) approach, 
 * using constants, messages, dates/times/number formats and plurals; and shows how this approach 
 * can even be used by ClientBundles.
 * 
 * <ul>
 *    <li>Messages are created both in the UiBinder declarative interface (StaticExample.ui.xml) for 
 *    the title and instructions, as well as the traditional approach (in client.i18nstatic.messages.ExampleMessages 
 *    class) for displaying the text about the current locale.</li>
 *    <li>Constants are in the client.i18nstatic.constants.CountryDetails class.</li>
 *    <li>Dates/Time formats come direct from GWTs DateTimeFormat formatting classes</li>
 *    <li>Number formats come from GWT's NumberFormat class, and is used on the speed limit of the country information</li>
 *    <li>Plurals for displaying how many "readers" there are (a number that can be manually updated through a drop down box on the screen) </li>
 *    <li>ClientBundles - harnessing the static approach to select the appropriate flag for a locale from a resource bundle</li>
 * </ul>
 *
 */
public class StaticExample extends Composite{
	
	// Simple enumeration for the types of date/time display
    enum formats{ FULL, LONG, MEDIUM, SHORT}
    
    int numberOfReaders = 5;
    Gender gen = Gender.MALE;

    // UI elements we will access
    @UiField SpanElement date;
    @UiField (provided=true) ListBox formatChoice;    
    @UiField (provided=true) ListBox gender;    
    @UiField (provided=true) ListBox locale;
    @UiField TextBox numberReaders;
    @UiField Label numReaders;
    @UiField SpanElement salary;
    @UiField SpanElement speedLimit;
    @UiField FlowPanel textArea;
    @UiField SpanElement time;
    @UiField HTMLPanel xssTestField;

    
    // UiBinder template code
    interface StaticExampleUiBinder extends UiBinder<Widget, StaticExample> {}
    private static StaticExampleUiBinder uiBinder = GWT.create(StaticExampleUiBinder.class);

    
    // Simple constructor
    public StaticExample(){
    	locale = new ListBox();
		formatChoice = new ListBox();
		gender = new ListBox();


    	initWidget(uiBinder.createAndBindUi(this));
    	this.setSize("100%", "100%");
    	addSelectItems();
    	showLocaleInformation();
    	showCountryDetails();
    	showReaders(gen, numberOfReaders);
    }
    
    
    // Adds items to the Date/Time format dropdown box
    private void addSelectItems(){
		formatChoice.addItem(formats.FULL.toString());
		formatChoice.addItem(formats.LONG.toString());
		formatChoice.addItem(formats.SHORT.toString());
		formatChoice.addItem(formats.MEDIUM.toString());
		for (String localeName : LocaleInfo.getAvailableLocaleNames()) {
			  locale.addItem(LocaleInfo.getLocaleNativeDisplayName(localeName));
		}
		gender.addItem("MALE");
		gender.addItem("FEMALE");
    }
    
    
    /**
     * If the locale is changed through the drop down Locale box, set the locale in the URL
     * and reload the page.
     * @param event
     */
    @UiHandler("locale")
    void addTextToScreen (ChangeEvent event){
		locale.getItemText(locale.getSelectedIndex());
		String[] ls = LocaleInfo.getAvailableLocaleNames();
		String localeToUse = ls[locale.getSelectedIndex()];
		UrlBuilder newUrl = Window.Location.createUrlBuilder();
		newUrl.setParameter("locale", localeToUse);
		Window.Location.assign(newUrl.buildString());
    }
    
   
    /**
     * Changes the format of the date and time.  This does not require a reload of the page
     * @param format New format to show
     */
    private void changeDateTimeFormat(String format){
    	format = format.toLowerCase();
    	Date today = new Date();
    	if (format.equals(formats.FULL.toString().toLowerCase())){
        	date.setInnerText(DateTimeFormat.getFormat(PredefinedFormat.DATE_FULL).format(today));
        	time.setInnerText(DateTimeFormat.getFormat(PredefinedFormat.TIME_FULL).format(today));
    	} else if (format.equals(formats.LONG.toString().toLowerCase())){
        	date.setInnerText(DateTimeFormat.getFormat(PredefinedFormat.DATE_LONG).format(today));
        	time.setInnerText(DateTimeFormat.getFormat(PredefinedFormat.TIME_LONG).format(today));
    	} else if (format.equals(formats.SHORT.toString().toLowerCase())){
        	date.setInnerText(DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT).format(today));
        	time.setInnerText(DateTimeFormat.getFormat(PredefinedFormat.TIME_SHORT).format(today));
    	} else /* MEDIUM */{
        	date.setInnerText(DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM).format(today));
        	time.setInnerText(DateTimeFormat.getFormat(PredefinedFormat.TIME_MEDIUM).format(today));
    	} 
    }
    
    
    /**
     * Handle the date/time format drop down box changing value
     */
    @UiHandler("formatChoice")
    void changeDateTimeFormatChoice (ChangeEvent event){
    	// Get the new value
    	String val = formatChoice.getItemText(formatChoice.getSelectedIndex());
    	// Make the changes
    	changeDateTimeFormat(val);
    }
    
    /**
     * Handle the gender  drop down box changing value
     */
    @UiHandler("gender")
    void changeGenderChoice (ChangeEvent event){
    	// Get the new value
    	Window.alert("Changing: "+Gender.valueOf(gender.getItemText(gender.getSelectedIndex())).getName());
    	gen = Gender.valueOf(gender.getItemText(gender.getSelectedIndex()));
    	showReaders(gen, numberOfReaders);
    }

    @UiField Label readerInfo;
    
    
    /**
     * Handle the clicking of the Number of Readers Change button
     */
    @UiHandler("numberReadersButton")    
    void changeNumberReaders(ClickEvent event){
    	// Find out the new number
    	String num = numberReaders.getValue();
    	try{
    		// Update the Number of Readers message
    		numberOfReaders = Integer.parseInt(num);
    		showReaders(gen, numberOfReaders);
    	} catch (NumberFormatException e) {
    		// If it wasn't a number, let the user know that.
    		Window.alert("Number of readers must be an integer");
    	}
    }


    /**
     * Display the country details, which are all held as constants in the 
     * client.i18nstatic.constant.CountryDetails class.
     * 
     * The salary information is displayed through a NumberFormater so that it gains
     * the appropriate currency symbol, in the expected place, and the number is formatted
     * as would be expected for that locale (e.g. use of , or . for decimal separater etc) 
     */
    private void showCountryDetails(){
    	// Create an instance of the constants via deferred binding
    	CountryDetails det = (CountryDetails)GWT.create(CountryDetails.class);
    	
    	// Set the speed limit Label's inner text using two constants from the CountryDetails class
    	speedLimit.setInnerText(det.speedLimit()+" "+det.speedUnits());
    	
    	// Get a NumberFormat class for currency formatting
    	NumberFormat fmt = NumberFormat.getCurrencyFormat();
    	// Display the Salary from CountryDetails after it is formatted by the above currency formatter
    	salary.setInnerText(fmt.format(det.averageSalary()));
    	
    	// For the first display, set the date and time formatter to full
    	changeDateTimeFormat(formats.FULL.toString());
    }

    /**
     * Show information on the UI about the locale.
     * 
     * Locale name is retrieved from the LocaleInfo object, and is displayed in a message created from ExampleMessages
     * 
     * Flag of locale is retrieved from a ClientBundle, which just so happens to adhere to the same rules as locale
     * 
     */
    private void showLocaleInformation(){
    	// Create an instance of the ExampleMessages class via deferred Binding
    	ExampleMessages msgs = (ExampleMessages)GWT.create(ExampleMessages.class);
    	
       	// Get name of current locale from the LocaleInfo object
		String localName = LocaleInfo.getLocaleNativeDisplayName(LocaleInfo.getCurrentLocale().getLocaleName());
		// Create message telling user what the current locale is
    	textArea.add(new Label(msgs.locale(localName)));
    	
    	// Create the LocaleFlags CientBundle instance via deferred binding
    	LocaleFlags flags = (LocaleFlags)GWT.create(LocaleFlags.class);
    	// Add the flag of the locale defined in the client bundle.
    	textArea.add(new Image(flags.getLocaleFlag()));
    }

    // Update the Number of users message, using plurals defined in the ExampleMessages class(es)
    private void showReaders(Gender gen, int num){
    	// Create an instance of the ExampleMessages class via deferred binding
    	ExampleMessages msgs = (ExampleMessages)GWT.create(ExampleMessages.class);
    	// Create the message - notice the parameter num that is passed in and is used by GWT to create the appropriate
    	// pluralisation of the message (e.g. 1 person is; 2 people are)
    	numReaders.setText(msgs.currentReaders(num));    	
    	readerInfo.setText(msgs.likesReading(gen.getName(), num, gen));
    }
        
    
    // Show message with an attack (alerts a window) but returned as a SafeHTML object, i.e. attack fails.
    @UiHandler("attackSafeHTML")
    public void showAttackSafeHTML(ClickEvent evt){
    	ExampleMessages msgs = (ExampleMessages)GWT.create(ExampleMessages.class);
    	xssTestField.clear();
    	HTMLPanel test = new HTMLPanel(msgs.hackAttackSafeHTML("<img src=\"gwtia_ch13_i18n/clear.cache.gif\" onload=\"window.alert('You have been attacked!!');\">"));//"<img src=\"gwtia_ch13_i18n/clear.cache.gif\" onload=\"window.alert('XSS');\">");
    	xssTestField.add(test);
    }
    
    // Show message with an attack returned as a String object, i.e. attack succeeds and JavaSCript is executed.
    @UiHandler("attackString")
    public void showAttackString(ClickEvent evt){
    	ExampleMessages msgs = (ExampleMessages)GWT.create(ExampleMessages.class);
    	xssTestField.clear();
    	HTMLPanel test = new HTMLPanel(msgs.hackAttackString("<img src=\"gwtia_ch13_i18n/clear.cache.gif\" onload=\"window.alert('You have been attacked!!');\">"));//"<img src=\"gwtia_ch13_i18n/clear.cache.gif\" onload=\"window.alert('XSS');\">");
    	xssTestField.add(test);
    }
}
