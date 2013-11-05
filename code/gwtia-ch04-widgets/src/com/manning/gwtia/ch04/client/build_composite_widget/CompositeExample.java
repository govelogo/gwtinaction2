/**
 * 
 */
package com.manning.gwtia.ch04.client.build_composite_widget;

// The rest of these imports just relate to standard user interface aspects.
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

/**
 * This example shows how to create a widget directly from the DOM.
 * 
 * The example builds a simple canvas widget (although GWT 2.2. introduces a
 * specific GWT widget, we keep this example as it shows how to build a widget)
 * 
 */
public class CompositeExample extends Composite {

	/**
	 * A new ReportSizeLabel object - this is defined in ReportSizeLabel.java
	 * and is an extension of the InlineLabel from GWT whose onLoad method is
	 * overloaded to display a message to the user of the width and height.
	 */
	DataField questionLTR;
	DataField questionRTL;

	/**
	 * Creates the Widget. Sets up history handling, the GUI components, and
	 * button handling.
	 */
	public CompositeExample() {
		FlowPanel display = new FlowPanel();

		final DataField questionLTR = new DataField("Question One ");
		final Label answerLTR = new Label("LTR Answer is: ");
		DataField questionRTL = new DataField("سؤال واحد");
		questionRTL.setDirection(Direction.RTL);
		display.add(questionLTR);
		display.add(answerLTR);
		display.add(questionRTL);

		initWidget(display);

		questionLTR.addMouseOverHandler(new MouseOverHandler() {
			public void onMouseOver(MouseOverEvent event) {
				questionLTR.getElement().getStyle()
						.setBorderStyle(BorderStyle.DOTTED);
				questionLTR.getElement().getStyle().setBorderWidth(5, Unit.PX);
			}
		});

		questionLTR.addValueChangeHandler(new ValueChangeHandler<String>() {
			public void onValueChange(ValueChangeEvent<String> event) {
				answerLTR.setText("LTR Answer is: " + event.getValue());
			}
		});

	}
}