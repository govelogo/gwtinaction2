package com.manning.gwtia.ch06.client.disclosure;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DisclosurePanelExample extends Composite
{

  private static DisclosurePanelExampleUiBinder uiBinder = GWT
      .create(DisclosurePanelExampleUiBinder.class);

  interface DisclosurePanelExampleUiBinder
      extends UiBinder<Widget, DisclosurePanelExample>
  {
  }

  public DisclosurePanelExample() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}
