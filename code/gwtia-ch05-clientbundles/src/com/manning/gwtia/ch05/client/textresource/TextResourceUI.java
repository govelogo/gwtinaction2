package com.manning.gwtia.ch05.client.textresource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class TextResourceUI extends Composite {

  private static TextResourceUIUiBinder uiBinder = GWT
      .create(TextResourceUIUiBinder.class);

  interface TextResourceUIUiBinder extends UiBinder<Widget, TextResourceUI> {
  }

  @UiField
  Resources res;
  @UiField
  Label etextLabel;

  public TextResourceUI() {
    initWidget(uiBinder.createAndBindUi(this));

    try {
      res.extText().getText(new ResourceCallback<TextResource>() {
        public void onSuccess(TextResource resource) {
          etextLabel.setText(resource.getText());
        }
        public void onError(ResourceException e) {
          Window.alert("Failed to retrieve ExternalTextResource:\n"
              + e.getMessage());
        }
      });

    } catch (ResourceException e) {
      Window.alert("Failed to retrieve ExternalTextResource:\n"
          + e.getMessage());
    }
  }

}
