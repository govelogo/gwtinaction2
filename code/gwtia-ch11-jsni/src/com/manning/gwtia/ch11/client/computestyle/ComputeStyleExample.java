/**
 * 
 */
package com.manning.gwtia.ch11.client.computestyle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;

import com.google.gwt.user.client.ui.Widget;

/**
 *  This example shows the use of a JSNI function for getting computed style of a DOM element.
 *  
 *  It displays two boxes on the screen with some text in, one inside the other.  You can change the colour
 *  of text in both boxes via some drop down boxes.
 *  
 *  If you change the colour in box 1, but leave box 2 as [none], then box 2 text inherits the colour of box 1 text.
 *  
 *  There are two text boxes that show the colour of text in box 2.  The first box shows the result of standard 
 *  getStyle method, and will display [inherited] if there is no value from that method.  The second box shows the
 *  result of our new getComputedStyle method, which should always show a colour definition.
 *   
 */
public class ComputeStyleExample extends Composite{

	/**
	 * Use GWT Deferred Binding to get an instance of MyStyle for use with the appropriate
	 * user agent.
	 * 
	 * We expect to get an instance of MyStyle for IE, and MyStylStandard for standards compliant
	 * browsers.  This rule is defined in the ComputedStyleExample.gwt.xml module.
	 * 
	 */
    MyStyle myStyle = GWT.create(MyStyle.class);
	
	// UiBinder template code
    interface ComputeStyleExampleUiBinder extends UiBinder<Widget, ComputeStyleExample> {}
    private static ComputeStyleExampleUiBinder uiBinder = GWT.create(ComputeStyleExampleUiBinder.class);
	
    // The two drop down boxes.
    @UiField(provided=true) ListBox box1Select;
    @UiField(provided=true) ListBox box2Select;

    // The two boxes - so we can set their colours
    @UiField FlowPanel box1;
    @UiField FlowPanel box2;
    
    // The two text fields, so we can set the text
    @UiField TextArea styleDef;
    @UiField TextArea computedStyleDef;

    
    
    // Create the user interface, and add the options to the drop down boxes
    public ComputeStyleExample(){
    	box1Select = new ListBox();
    	box1Select.addItem("[none]");
    	box1Select.addItem("red");
    	box1Select.addItem("green");
    	box1Select.addItem("white");
    	box1Select.addItem("blue");
    	box1Select.addItem("orange");
    	box1Select.setSelectedIndex(3);
    	
    	box2Select = new ListBox();
    	box2Select.addItem("[none]");
    	box2Select.addItem("red");
    	box2Select.addItem("green");
    	box2Select.addItem("white");
    	box2Select.addItem("blue");
    	box2Select.addItem("orange");
    	box2Select.setSelectedIndex(0);

    	initWidget(uiBinder.createAndBindUi(this));
    	this.setSize("100%", "100%");
    }  
    
    
    /**
     * When the widget has loaded into the DOM, call the setStlye and setComputedStyle methods to
     * display the current colour of text in box 2.
     */
    @Override
    public void onLoad(){
    	setStyle();
    	setComputedStyle();
    }
    

    /**
     *  Set the text field for the style definition (use [inherited] if no value is
     *  returned from the getStyle().getColor() method.
     */
    private void setStyle(){
    	String val = box2.getElement().getStyle().getColor();
    	if ((val==null)||(val.equals(""))) val = "[inherited]";
		styleDef.setText(val);
    }
    
    
    /**
     * Set the text field for the ComputedStyle definition (use [inherited] if no value is
     *  returned from the getComputedStyle() method.
     */
    private void setComputedStyle(){
    	String val = myStyle.getComputedStyle(box2.getElement(), "color");
    	if ((val==null)||(val.equals(""))) val = "[inherited]";
		computedStyleDef.setText(val);
    }
        
    /**
     * Handle the change of colour in drop down for box 1.
     */
    @UiHandler("box1Select")
    void changeBox1Color (ChangeEvent event){
    	String val = box1Select.getItemText(box1Select.getSelectedIndex());
    	changeColor(box1, val);
    }

    /**
     * Handle the change of colour in drop down for box 2.
     */
    @UiHandler("box2Select")
    void changeBox2Color (ChangeEvent event){
    	String val = box2Select.getItemText(box2Select.getSelectedIndex());
    	changeColor(box2, val);
    }
    
    
    /**
     * Change the colour of a widget, and then show the new colour of text in box 2 in the text fields.
     * @param w Widget to change colour
     * @param col Colour to change to
     */
    private void changeColor(Widget w, String col){
    	if(col.equals("[none]"))col = "";
    	w.getElement().getStyle().setColor(col);
    	setStyle();
    	setComputedStyle();
    }
}
