/**
 * 
 */
package com.manning.gwtia.ch04.client.layout;

// We will be using a LayoutPanel in this example
import com.google.gwt.user.client.ui.LayoutPanel;
// We have to make our example extend ResizeComposite (rather than normal Composite)
import com.google.gwt.user.client.ui.ResizeComposite;

// Standard imports
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * This example shows an example of LayoutPanel in action.
 * It has three coloured blocks that when clicking a button change position purely by altering layout constraints.
 * It also shows that Composites holding layout panels need to implement the ResizeComposite
 *
 */
public class LayoutExample extends ResizeComposite{

	/**
	 * Used to know how to toggle the animation of constraints
	 */
	boolean alreadyChanged = false;
	
	/**
	 * Variable to track latest zIndex to allow us to keep necessary panel on the top
	 */
	int zIndexCount = 4;
	
	/**
	 * The LayoutPanel that is essentially this widget.
	 */
	LayoutPanel holder;
	
	/**
	 * The button to click to animate the constraints (i.e. move the panels held inside)
	 */
	Button animateConstraints;
	
	// three simple panels that will be coloured and placed in the layout panel
	SimplePanel west;
	SimplePanel east;
	SimplePanel center;
	
	
	/**
	 * Used to add a ClickHandler to the animate button.  when clicked the constraints of 
	 * the LayoutPanel are changed, and then the animate() method is called to animate the
	 * panel to the new shape.
	 */
	private void setUpEventHandling(){

		// Add a click handler to the button
		animateConstraints.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				if(!alreadyChanged){
					// Move the center panel to the right
					holder.setWidgetTopHeight(center, 0, Unit.PCT, 100, Unit.PCT);
					holder.setWidgetRightWidth(center, 0, Unit.PCT, 50, Unit.PCT);
					// Move right panel to the left
					holder.setWidgetLeftWidth(east, 0, Unit.PCT, 50, Unit.PCT);
					// Move left panel to the center
					holder.setWidgetLeftRight(west, 25, Unit.PCT, 25, Unit.PCT);
					holder.setWidgetTopBottom(west, 25, Unit.PCT, 25, Unit.PCT);
					// Update the zIndex of left panel, otherwise it will be hidden.
					west.getElement().getStyle().setZIndex(zIndexCount++);
				} else {
					// Move everything back to starting position
					holder.setWidgetTopHeight(west, 0, Unit.PCT, 100, Unit.PCT);
					holder.setWidgetLeftWidth(west, 0, Unit.PCT, 50, Unit.PCT);
					holder.setWidgetRightWidth(east, 0, Unit.PCT, 50, Unit.PCT);
					holder.setWidgetLeftRight(center, 25, Unit.PCT, 25, Unit.PCT);
					holder.setWidgetTopBottom(center, 25, Unit.PCT, 25, Unit.PCT);
					center.getElement().getStyle().setZIndex(zIndexCount++);
				}
				// Run the animation between current constraints and the ones we have just set above.
				holder.animate(500);
				alreadyChanged = !alreadyChanged;
			}			
		});
	}
	


	private LayoutPanel setUpGui() {
		
		// Create the layout panel
		holder = new LayoutPanel();
		
		// Create some simple panels that we will color to use in the display
		// they are "final" as we use them in an anonymous class later
		west = new SimplePanel();
		east = new SimplePanel();
		center = new SimplePanel();

		// Add the panels to the holder
		holder.add(west);
		holder.add(east);
		holder.add(center);
		
		// Make the left panel fill the left 50% of the browser
		holder.setWidgetLeftWidth(west, 0, Unit.PCT, 50, Unit.PCT);
		// Make the right panel fill the right 50% of the browser
		holder.setWidgetRightWidth(east, 0, Unit.PCT, 50, Unit.PCT);
		// Make the center panel fill the 50% of the middle of the screen by setting its
		// top, left, right and bottom limits to 25%
		holder.setWidgetLeftRight(center, 25, Unit.PCT, 25, Unit.PCT);
		holder.setWidgetTopBottom(center, 25, Unit.PCT, 25, Unit.PCT);

		// MUST CALL THIS METHOD to set the constraints; if you don't not much will be displayed!
		holder.forceLayout();

		
		// Add some labels and a button to the panels
		center.add(new Label("Center Panel"));
		east.add(new Label("East Panel"));
		
		animateConstraints = new Button("Click to animate constraints");
		VerticalPanel vp = new VerticalPanel();
		vp.add(new Label("West Panel"));
		vp.add(animateConstraints);
		west.add(vp);
		
		
		// Throw in some colors and a white border so we can easily see the panels.
		// This would normally be done in a CSS style sheet, but we use GWT's DOM handling
		// methods here for visibility of what we are doing.
		west.getElement().getStyle().setBackgroundColor("lightblue");
		west.getElement().getStyle().setProperty("border", "solid 10px white");
		east.getElement().getStyle().setBackgroundColor("yellow");
		east.getElement().getStyle().setProperty("border", "solid 10px white");
		center.getElement().getStyle().setBackgroundColor("red");
		center.getElement().getStyle().setProperty("border", "solid 10px white");
		
		return holder;
	}
	

	/**
	 * Sets up the widget.
	 * We create the GUI, set up event handling, and call initWidget on the holder panel.
	 */
	public LayoutExample(){
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
