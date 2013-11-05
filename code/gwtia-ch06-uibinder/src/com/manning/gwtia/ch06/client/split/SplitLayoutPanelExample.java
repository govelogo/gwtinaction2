package com.manning.gwtia.ch06.client.split;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SplitLayoutPanelExample extends Composite
{

  private static SplitLayoutPanelExampleUiBinder uiBinder = GWT
      .create(SplitLayoutPanelExampleUiBinder.class);

  interface SplitLayoutPanelExampleUiBinder
      extends UiBinder<Widget, SplitLayoutPanelExample>
  {
  }

  public SplitLayoutPanelExample() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}
