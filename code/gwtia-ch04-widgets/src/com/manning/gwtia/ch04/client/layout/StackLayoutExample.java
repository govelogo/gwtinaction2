/**
 * 
 */
package com.manning.gwtia.ch04.client.layout;

// We will be using a LayoutPanel in this example
// We have to make our example extend ResizeComposite (rather than normal Composite)
import com.google.gwt.user.client.ui.ResizeComposite;

// Standard imports
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;

/**
 * This example shows an example of LayoutPanel in action.
 * It has three coloured blocks that when clicking a button change position purely by altering layout constraints.
 * It also shows that Composites holding layout panels need to implement the ResizeComposite
 *
 */
public class StackLayoutExample extends ResizeComposite{

	
	/**
	 * The LayoutPanel that is essentially this widget.
	 */
	StackLayoutPanel holder;
	
	/**
	 * The button to click to animate the constraints (i.e. move the panels held inside)
	 */
	Button animateConstraints;
	
	// three simple panels that will be coloured and placed in the layout panel
	SimplePanel one;
	SimplePanel two;
	SimplePanel three;
	SimplePanel four;
	SimplePanel five;
	
	final double HEADER_SIZE = 40;
	
	
	/**
	 * Add a ClickHandler to the animateConstraint buttons so that when it is clicked
	 * the LayoutPanel returns to its original shape.
	 */
	private void setUpEventHandling(){
	}
	


	private StackLayoutPanel setUpGui() {
		
		// Create the layout panel
		holder = new StackLayoutPanel(Unit.PX);
		
		// Create some simple panels that we will color to use in the display
		// they are "final" as we use them in an anonymous class later
		one = new SimplePanel();
		two = new SimplePanel();
		three = new SimplePanel();
		four = new SimplePanel();
		five = new SimplePanel();

		// Add the panels to the holder
		holder.add(one, "First", HEADER_SIZE);
		holder.add(two, "Second", HEADER_SIZE);
		holder.add(three, "Third", HEADER_SIZE);
		holder.add(four, "Fourth", HEADER_SIZE);
		holder.add(five, "Fifth", HEADER_SIZE);
		
		
		// MUST CALL THIS METHOD to set the constraints; if you don't not much will be displayed!
		holder.forceLayout();

		
		// Throw in some colors and a white border so we can easily see the panels.
		// This would normally be done in a CSS style sheet, but we use GWT's DOM handling
		// methods here for visibility of what we are doing.
		one.getElement().getStyle().setBackgroundColor("lightblue");
		two.getElement().getStyle().setBackgroundColor("yellow");
		three.getElement().getStyle().setBackgroundColor("purple");
		four.getElement().getStyle().setBackgroundColor("lightgreen");
		five.getElement().getStyle().setBackgroundColor("red");
		
		return holder;
	}
	

	/**
	 * Sets up the widget.
	 * We create the GUI, set up event handling, and call initWidget on the holder panel.
	 */
	public StackLayoutExample(){
		// Creat the GUI
		// Note that in this setUpGui method we call the needed forceLayout method to enforce the constraints
		// that are set up; not doing so means nothing would be displayed - it is only needed for layout panels.
		holder = setUpGui();
		// Set up handling of events for the clicking of the button to animate constraints.
		setUpEventHandling();
		initWidget(holder);
		// ensure this widget takes up as much space as possible
		this.setSize("100%", "100%");
	}
}
