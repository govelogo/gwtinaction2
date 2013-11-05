/**
 * 
 */
package com.manning.gwtia.ch04.client.build_composite_widget;

import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.HasDirection;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Widget that demonstrates how to build a GWT Composite.
 * 
 * It consists of a Label (question) and a TextBox (answer) in a
 * HorizontalPanel.
 * 
 * In Ch11's Direction example we move away from the explicit setting of
 * direction to determining direction through estimators (e.g. if there are more
 * RTL words than LTR the diretion of Composite and components would be RTL)
 * 
 */
public class DataField extends Composite implements HasText, HasDirection {

	// The UI elements we will manipulate
	InlineLabel theQuestion;
	TextBox theAnswer;
	FlowPanel panel;

	protected Direction dir = Direction.DEFAULT;

	/**
	 * Construct the widget
	 */
	public DataField(String question) {
		panel = new FlowPanel();
		theQuestion = new InlineLabel(question);
		theAnswer = new TextBox();
		buildDisplay();

		initWidget(panel);

		this.getElement().getStyle()
				.setProperty("border", "solid lightblue 2px");
	}

	private void buildDisplay() {
		panel.clear();
		theQuestion.setText(theQuestion.getText(), dir);
		theAnswer.setDirection(dir);
		if (dir.equals(Direction.RTL)) {
			panel.add(theAnswer);
			panel.add(theQuestion);
		} else {
			panel.add(theQuestion);
			panel.add(theAnswer);
		}
	}

	// Utility method to get the answer text
	public String getText() {
		String answer = "";
		if (theAnswer != null)
			answer = theAnswer.getText();
		return answer;
	}

	// Utility method to get the question text
	public String getQuestion() {
		String question = "";
		if (theQuestion != null)
			question = theQuestion.getText();
		return question;
	}

	// Utility method to set the answer text
	public void setText(String text) {
		if (theAnswer != null)
			theAnswer.setText(text);
	}

	// Utility method to set the question text
	public void setQuestion(String text) {
		if (theQuestion != null)
			theQuestion.setText(text);
	}

	public void setDirection(Direction direction) {
		this.dir = direction;
		buildDisplay();
	}

	public Direction getDirection() {
		return dir;
	}

	public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
		return addDomHandler(handler, MouseOverEvent.getType());
	}

	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<String> handler) {
		return theAnswer.addValueChangeHandler(handler);
	}
}
