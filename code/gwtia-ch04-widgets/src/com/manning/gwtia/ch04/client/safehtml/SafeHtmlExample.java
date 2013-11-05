/**
 * 
 */
package com.manning.gwtia.ch04.client.safehtml;

// The rest of these imports just relate to standard user interface aspects.
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.StackLayoutPanel;

/**
 * This example shows how to create a widget directly from the DOM.
 * 
 * The example builds a simple canvas widget (although GWT 2.2. introduces a specific GWT
 * widget, we keep this example as it shows how to build a widget)
 * 
 */
public class SafeHtmlExample extends Composite{

	StackLayoutPanel container = new StackLayoutPanel(Unit.PX);
	
	/**
	 * Creates the Widget.
	 */
	public SafeHtmlExample(){

		container.add(new InnerHTMLExample(), "Testing innerHtml()", 30);
		container.add(new SetTextExample(), "Testing widget setText()", 30);
		container.add(new SafeHtmlBuilderExample(), "Testing SafeHtmlBuilder", 30);
		container.add(new SafeHtmlUtilsExample(), "Testing SafeHtmlUtils", 30);
		container.add(new SafeHtmlSanitizerExample(), "Testing SafeHtmlSanatizer", 30);
		container.add(new SafeHtmlTemplateExample(), "Testing SafeHtml Templates", 30);

		initWidget(container);
		this.setSize("100%", "100%");
		container.forceLayout();

		
	}
}