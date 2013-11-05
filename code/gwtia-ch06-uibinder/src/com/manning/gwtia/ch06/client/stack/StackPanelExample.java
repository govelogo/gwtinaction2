package com.manning.gwtia.ch06.client.stack;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class StackPanelExample extends Composite
{

  private static StackPanelExampleUiBinder uiBinder = GWT.create(StackPanelExampleUiBinder.class);

  interface StackPanelExampleUiBinder extends UiBinder<Widget, StackPanelExample>{}

  public StackPanelExample() {
    initWidget(uiBinder.createAndBindUi(this));
  }

}
