package com.manning.gwtia.ch04.client.safehtml;

import com.google.gwt.user.client.ui.HTMLPanel;

public class InnerHTMLExample extends BaseExample {


	@Override
	void setUpInstr() {
		instr1.setText("Example shows use of setInnerText and setInnerHtml");
		instr2.setText("Type text into area below, or click on \"insert hack attack\"; select the option and...");
		instr3.setText("...click use button");
	}

	@Override
	void setRadioText() {
		safe.setText("DOM.setInnerText");
		unsafe.setText("DOM.setInnerHTML");
	}

	@Override
	void setUpDisplay() {
		pong = new HTMLPanel("");
		container.add(pong);
	}

	@Override
	void handleSafe() {
		pong.getElement().setInnerText("Setting innerText: "+ping.getText());		
	}

	@Override
	void handleUnsafe() {
		pong.getElement().setInnerHTML("Setting innerHTML: "+ping.getText());	
	}

}
