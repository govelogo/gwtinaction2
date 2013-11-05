package com.manning.gwtia.ch14.client.eventEfficiency;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class EventEfficiencyExample extends ResizeComposite{
	
    private static EventEfficiencyExampleUiBinder uiBinder = GWT.create(EventEfficiencyExampleUiBinder.class);
    interface EventEfficiencyExampleUiBinder extends UiBinder<Widget, EventEfficiencyExample> {}
    
    
    @UiField FlowPanel comment;
    @UiField ScrollPanel scroller;


    String text = "Some Text: ";
    int counter = 100;
    final String BASE_COMMENT = "Event: ";
    
	public EventEfficiencyExample(){
		initWidget(uiBinder.createAndBindUi(this));
        this.setSize("100%", "100%");
	}


	@UiHandler({
		"butt00", "butt01", "butt02", "butt03",
		"butt10", "butt11", "butt12", "butt13",
		"butt20", "butt21", "butt22", "butt23",
		"butt30", "butt31", "butt32", "butt33",})
	void handleClick(ClickEvent click){
		Button sender = (Button) click.getSource();
		addMessage("You clicked Button: "+sender.getText());
	}

	private void addMessage(String msg){
		Label lbl = new Label(msg);
		comment.add(lbl);
		scroller.ensureVisible(lbl);
	}
	
	@UiHandler("clear")
	void clearScroller(ClickEvent click){
		comment.clear();
	}
	
}
