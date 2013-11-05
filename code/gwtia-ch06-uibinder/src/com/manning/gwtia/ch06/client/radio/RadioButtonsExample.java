package com.manning.gwtia.ch06.client.radio;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class RadioButtonsExample extends Composite
{
  private static RadioButtonsExampleUiBinder uiBinder = GWT.create(RadioButtonsExampleUiBinder.class);
  interface RadioButtonsExampleUiBinder extends UiBinder<Widget, RadioButtonsExample> {}

  public RadioButtonsExample() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}
