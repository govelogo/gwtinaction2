/**
 * 
 */
package com.manning.gwtia.ch13.client.i18nstatic.messages;



import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;
import com.google.gwt.safehtml.shared.SafeHtml;

/**
 * An interface of Messages.  It contains messages for
 * 
 * <ul>
 *    <li>How many users are reading a page - demonstrates plural messages in action</li>
 *    <li>A simple message for naming the locale</li>
 * </ul>
 * 
 * The translations for the messages in this file can be found in the various ExampleMessages_xx properties files
 * in this package.
 * 
 * (Other messages are used by this application, but are defined in the UiBinder file StaticExample.ui.xml using
 * the <ui:msg> tag; the message definitions can be found in StaticExampleStaticExampleUiBinderImplGenMessages_xx.properties files).
 *
 * The first annotation below @DefaultLocale, specifies that en_US is the default locale for the application.
 *
 */
@DefaultLocale("en_US")
public interface ExampleMessages extends Messages{

	/**
	 * A simple message that indicates what the locale is
	 * @param locale
	 * @return The appropriate pluralised message (all linkages to properties files are done by GWT at compile time)
	 */
	@DefaultMessage("The current locale is: {0}")
	public String locale(String locale);
	

	/**
	 * A message showing how many "users" are "reading" the current page
	 * Note that @PluralText has been deprecated in favour of @AlternateMessage.
	 * @param numberReaders The number of readers.
	 * @return The appropriate pluralised message (all linkages to proerties files are done by GWT at compile time)
	 */

	// We provide a default message....
	@DefaultMessage("There are {0} other people reading this page")
	// ... and an alternative to be used when only 1 person is reading the page - which changes the "are" to "is" in English.
	// (see other properties files for alternatives when other languages are used that have different rules, e.g. the French locale needs 3 plural forms)
	@AlternateMessage({"one", "There is {0} other user reading this page",
		               "=0", "There are no other users reading this page"})
	public String currentReaders(@PluralCount int numberReaders);
	
	
	// An enumeration for Gender we will use as a selection criteria in the next message
	// (slightly more complicated than needed as we encode a name in there for asthetics in the message)
	enum Gender {MALE("Jan"), FEMALE("Johanna");
					private String name;
					Gender(String name){
						this.name = name;
					};
					public String getName(){return name;}
				}
	
	// We provide a default message....
	@DefaultMessage("The readers like reading their chosen web pages")
	// ... and an alternative when Gender is FEMALE
	@AlternateMessage({"one|MALE", "{0} likes reading his chosen web pages",
					   "one|FEMALE", "{0} likes reading her chosen web pages",
					   "=0|MALE", "", 
					   "=0|FEMALE", ""})
	public String likesReading(@Optional String name, @Optional @PluralCount int numberReaders, @Optional @Select Gender gender);
	
	@DefaultMessage("<b>{0}</b>")
	@Description("A potential hack that is neutralised by returning SafeHtml object instead of String")
	public SafeHtml hackAttackSafeHTML(String msg);
	
	@DefaultMessage("<b>{0}</b>")
	@Description("A potential hack that is not neutralised as we return String object instead of SafeHtml")
	public String hackAttackString(String msg);
}
