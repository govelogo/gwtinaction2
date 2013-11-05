package com.manning.gwtia.ch06.client.login2;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;

public class LoginDialogBox_v2 extends PopupPanel {
  interface MyBinder extends UiBinder<Widget, LoginDialogBox_v2> {}
  private static MyBinder binder = GWT.create(MyBinder.class);

  @UiField Button btnLogin;
  @UiField TextBox txtEmail;

  @UiField SpanElement eEmailErrorText;
  @UiField SpanElement ePassErrorText;
  @UiField Element eEmailError; 
  @UiField Element ePassError;

  @UiField(provided = true) TextBox txtPassword;

  public LoginDialogBox_v2() {
    setStyleName("");
    txtPassword = new PasswordTextBox();
    add(binder.createAndBindUi(this));
  }
}
