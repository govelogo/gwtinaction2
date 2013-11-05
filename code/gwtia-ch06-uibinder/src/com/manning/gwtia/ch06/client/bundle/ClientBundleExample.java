package com.manning.gwtia.ch06.client.bundle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ClientBundleExample extends Composite
{
  private static ClientBundleExampleUiBinder uiBinder = GWT.create(ClientBundleExampleUiBinder.class);
  interface ClientBundleExampleUiBinder extends UiBinder<Widget, ClientBundleExample> {}


  public ClientBundleExample() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  /**
   * Using a factory for this resource bundle allows us to use the static INSTANCE
   * of the bundle and ensures the CSS resource has been injected.  Without this factory
   * a new instance of the bundle would be created each time this widget is created,
   * and the CSS bundle would not be properly initialized.
   */
  @UiFactory
  public MyBundle createTheBundle () {
    MyBundle.INSTANCE.style().ensureInjected();
    return MyBundle.INSTANCE;
  }

}
