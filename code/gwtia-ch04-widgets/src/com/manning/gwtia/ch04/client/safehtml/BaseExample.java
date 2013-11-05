package com.manning.gwtia.ch04.client.safehtml;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public abstract class BaseExample extends Composite {

	String dodgy = "<img src=\"gwtia_ch04_widgets/clear.cache.gif\" onload=\"window.alert('You have been attacked!!');\">";
	
	FlowPanel container;
	Label instr1 = new Label();
	Label instr2 = new Label();
	Label instr3 = new Label();
	HasText ping;
	Widget pong;
	RadioButton safe;
	RadioButton unsafe;
	FlowPanel buttonPanel;
	Button dodgyText;
	Button use;
	
	public BaseExample(){
		container = new FlowPanel();
		container.add(instr1);
		container.add(instr2);
		container.add(instr3);
		
		safe = new RadioButton("group1", "use InnerText");
		unsafe = new RadioButton("group1", "use InnerHtml");
		unsafe.setValue(true);
		buttonPanel = new FlowPanel();
		use = new Button("Use");
		buttonPanel.add(safe);
		buttonPanel.add(unsafe);
		buttonPanel.add(use);

		setUpInput();
		
		dodgyText = new Button("Insert hack attack text");
		dodgyText.addClickHandler(new ClickHandler(){
					@Override
					public void onClick(ClickEvent event) {
						ping.setText(dodgy);
					}
		});

			use.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					if (safe.getValue()) {
						handleSafe();
					} else {
						handleUnsafe();
					}
				}
			});
		
		setUpInstr();
		setRadioText();

		container.add(dodgyText);
		container.add((Widget)ping);
		container.add(buttonPanel);

		setUpDisplay();
		
		initWidget(container);
	}
	
	void setUpInput(){
		ping = new TextArea();
	}
	
	abstract void handleSafe();
	abstract void handleUnsafe();
	abstract void setUpInstr();	
	abstract void setRadioText();
	abstract void setUpDisplay();
}
