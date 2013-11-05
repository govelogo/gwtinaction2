/**
 * 
 */
package com.manning.gwtia.ch04.client.layout;

// We will be using a LayoutPanel in this example
// We have to make our example extend ResizeComposite (rather than normal Composite)
import com.google.gwt.user.client.ui.ResizeComposite;

// Standard imports
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * This example shows an example of LayoutPanel in action.
 * It has three coloured blocks that when clicking a button change position purely by altering layout constraints.
 * It also shows that Composites holding layout panels need to implement the ResizeComposite
 *
 */
public class DockLayoutExample extends ResizeComposite{

	
	/**
	 * The LayoutPanel that is essentially this widget.
	 */
	DockLayoutPanel holder;
	
	/**
	 * The button to click to animate the constraints (i.e. move the panels held inside)
	 */
	Button animateConstraints;
	
	// three simple panels that will be coloured and placed in the layout panel
	SimplePanel west;
	SimplePanel east;
	SimplePanel north;
	SimplePanel south;
	SimplePanel center;
	
	final double WEST_SIZE = 100;
	final int MIN_WEST_SIZE=75;
	final double EAST_SIZE = 100;
	final double NORTH_SIZE = 100;
	final double SOUTH_SIZE = 100;
	final double MIN_SIZE = 10;

	
	boolean animated = false;
	
	/**
	 * Add a ClickHandler to the animateConstraint buttons so that when it is clicked
	 * the LayoutPanel returns to its original shape.
	 */
	private void setUpEventHandling(){

		// Add a click handler to the button
		animateConstraints.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				if(animated){
					holder.setWidgetSize(west, WEST_SIZE);
					holder.setWidgetSize(east,EAST_SIZE);
					holder.setWidgetSize(north, NORTH_SIZE);
					holder.setWidgetSize(south, SOUTH_SIZE);
					holder.animate(500);
				} else {
					holder.setWidgetSize(west, MIN_SIZE);
					holder.setWidgetSize(east,MIN_SIZE);
					holder.setWidgetSize(north, MIN_SIZE);
					holder.setWidgetSize(south, MIN_SIZE);
					holder.animate(500);
				}
				animated = !animated;
			}			
		});
	}
	


	private DockLayoutPanel setUpGui() {
		
		// Create the layout panel
		holder = new DockLayoutPanel(Unit.PX);
		
		// Create some simple panels that we will color to use in the display
		// they are "final" as we use them in an anonymous class later
		west = new SimplePanel();
		north = new SimplePanel();
		east = new SimplePanel();
		south = new SimplePanel();
		center = new SimplePanel();

		// Add the panels to the holder
		holder.addNorth(north, NORTH_SIZE);
		holder.addSouth(south, SOUTH_SIZE);
		holder.addWest(west, WEST_SIZE);
		holder.addEast(east,EAST_SIZE);
		holder.add(center);
		
		
		// MUST CALL THIS METHOD to set the constraints; if you don't not much will be displayed!
		holder.forceLayout();

		
		// Add some labels and a button to the panels
		west.add(new Label("West Panel"));
		east.add(new Label("East Panel"));
		north.add(new Label("North Panel"));
		south.add(new Label("South Panel"));

		
		animateConstraints = new Button("Click to animate");
		VerticalPanel vp = new VerticalPanel();
		vp.add(new Label("Center Panel"));
		vp.add(animateConstraints);
		center.add(vp);
		
		
		// Throw in some colors and a white border so we can easily see the panels.
		// This would normally be done in a CSS style sheet, but we use GWT's DOM handling
		// methods here for visibility of what we are doing.
		west.getElement().getStyle().setBackgroundColor("lightblue");
		east.getElement().getStyle().setBackgroundColor("yellow");
		north.getElement().getStyle().setBackgroundColor("purple");
		south.getElement().getStyle().setBackgroundColor("lightgreen");
		center.getElement().getStyle().setBackgroundColor("red");
		
		return holder;
	}
	

	/**
	 * Sets up the widget.
	 * We create the GUI, set up event handling, and call initWidget on the holder panel.
	 */
	public DockLayoutExample(){
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
