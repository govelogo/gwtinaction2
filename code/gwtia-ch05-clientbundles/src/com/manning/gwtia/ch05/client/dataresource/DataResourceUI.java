package com.manning.gwtia.ch05.client.dataresource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DataResourceUI extends Composite{

  private static DataResourceUIUiBinder uiBinder = GWT
      .create(DataResourceUIUiBinder.class);

  interface DataResourceUIUiBinder extends UiBinder<Widget, DataResourceUI> {
  }

  public DataResourceUI() {
    initWidget(uiBinder.createAndBindUi(this));
  }
  @UiField
  DataResources res;
  @UiField
  Button docButton;

  public DataResourceUI(String firstName) {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @UiHandler("docButton")
  void onClick(ClickEvent e) {
    Window.open(res.document().getSafeUri().asString(), "_blank", "");
  }
}
