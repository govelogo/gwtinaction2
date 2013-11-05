package com.manning.gwtia.ch06.client.login1;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoginDialogBox_v1 extends PopupPanel
{
  interface MyBinder extends UiBinder<Widget, LoginDialogBox_v1>{}

  private static MyBinder uiBinder = GWT.create(MyBinder.class);

  public LoginDialogBox_v1() {
    setStyleName("");
    add(uiBinder.createAndBindUi(this));
  }
}
