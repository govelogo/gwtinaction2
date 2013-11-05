package com.manning.gwtia.ch17.client.computestyle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Label;

import com.google.gwt.user.client.ui.Widget;

public class ComputeStyleExample extends Composite{
	
    interface ComputeStyleExampleUiBinder extends UiBinder<Widget, ComputeStyleExample> {}
    private static ComputeStyleExampleUiBinder uiBinder = GWT.create(ComputeStyleExampleUiBinder.class);
	
    
    enum formats{ FULL, LONG, SHORT, MEDIUM};
    @UiField(provided=true) ListBox box1Select;
    @UiField(provided=true) ListBox box2Select;
    @UiField Label className;
    
    
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
    
    
    public void onLoad(){
    	setClassUsed();
    	setStyle();
    	setComputedStyle();
    }
    
    private void setClassUsed(){
    	className.setText(className.getText()+myStyle.getVersion());
    }
    
 
    @UiField FlowPanel box1;
    @UiField FlowPanel box2;
    @UiField TextArea styleDef;
    @UiField TextArea computedStyleDef;
    private void setStyle(){
    	String val = box2.getElement().getStyle().getColor();
    	if ((val==null)||(val.equals(""))) val = "[inherited]";
		styleDef.setText(val);
    }
    
    private void setComputedStyle(){
    	String val = myStyle.getComputedStyle(box2.getElement(), "color");
    	if ((val==null)||(val.equals(""))) val = "[inherited]";
		computedStyleDef.setText(val);
    }
    
    MyStyle myStyle = GWT.create(MyStyle.class);
    
    @UiHandler("box1Select")
    void changeBox1Color (ChangeEvent event){
    	String val = box1Select.getItemText(box1Select.getSelectedIndex());
    	changeColor(box1, val);
    }
    
    @UiHandler("box2Select")
    void changeBox2Color (ChangeEvent event){
    	String val = box2Select.getItemText(box2Select.getSelectedIndex());
    	changeColor(box2, val);
    }
    
    private void changeColor(Widget w, String col){
    	if(col.equals("[none]"))col = "";
    	w.getElement().getStyle().setColor(col);
    	setStyle();
    	setComputedStyle();
    }
    
    
}
