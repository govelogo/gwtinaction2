package com.manning.gwtia.ch04.client.safehtml;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;


public class SafeHtmlTemplateExample extends BaseExample {
	
	interface MyTemplate extends SafeHtmlTemplates {
		@Template("<b>{0}</b>")
		SafeHtml message(String text);
	}

	@Override
	void setUpInstr() {
		instr1.setText("Example shows use of setText and setHtml on a Label widget");
		instr2.setText("Type text into area below, or click on \"insert hack attack\"; select the option and...");
		instr3.setText("...click use button");
	}

	@Override
	void setRadioText() {
		safe.setText("Direct Text");
		unsafe.setText("Use SafeHtmlTemplate");
	}

	@Override
	void setUpDisplay() {
		pong = new HTML("");
		container.add(pong);
	}

	@Override
	void handleSafe() {
		((HasHTML)pong).setHTML("Setting text direct: "+ping.getText());
	}

	@Override
	void handleUnsafe() {
		MyTemplate t = GWT.create(MyTemplate.class);
		SafeHtml s = t.message(ping.getText());
		((HasHTML)pong).setHTML("Setting from SafeHtmlBuilder: "+ s.asString());		
	}	
}
