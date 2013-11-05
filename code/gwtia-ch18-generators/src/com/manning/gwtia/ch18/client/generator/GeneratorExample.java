package com.manning.gwtia.ch18.client.generator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class GeneratorExample extends Composite{

	FlowPanel container;
	
	public GeneratorExample(){
//		container = GWT.create(FlowPanel.class);
//		Label test = GWT.create(Label.class);
//		Button button = GWT.create(Button.class);
		
		container = new FlowPanel();
		Label test = new Label();
		Button button = new Button();
		
		initWidget(container);

		container.add(test);
		container.add(button);
		test.setText("The text of this label has been set!");
		button.setText("Add A Label");
		button.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				Label clickedMessage = GWT.create(Label.class);
				clickedMessage.setText("You added this label");
				container.add(clickedMessage);
			}
		});
	}
	
}
