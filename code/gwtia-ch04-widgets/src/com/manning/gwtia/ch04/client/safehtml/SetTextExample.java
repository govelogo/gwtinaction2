package com.manning.gwtia.ch04.client.safehtml;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasHTML;


public class SetTextExample extends BaseExample {

	@Override
	void setUpInstr() {
		instr1.setText("Example shows use of setText and setHtml on a Label widget");
		instr2.setText("Type text into area below, or click on \"insert hack attack\"; select the option and...");
		instr3.setText("...click use button");
	}

	@Override
	void setRadioText() {
		safe.setText("Label.setText");
		unsafe.setText("Label.setHTML");
	}

	@Override
	void setUpDisplay() {
		pong = new HTML("");
		container.add(pong);
	}

	@Override
	void handleSafe() {
		((HasText)pong).setText("Setting Text: "+ping.getText());	
	}

	@Override
	void handleUnsafe() {
		((HasHTML)pong).setHTML("Setting HTML: "+ping.getText());
	}

}
