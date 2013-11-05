package com.manning.gwtia.ch05.client.imageresource;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;

public class ImageExample extends Composite {
  public ImageExample() {
    ImageResource[] icons = new ImageResource[] {
        Resources.IMPL.blogger(),
        Resources.IMPL.delicious(),
        Resources.IMPL.digg(),
        Resources.IMPL.facebook(),
        Resources.IMPL.google(),
        Resources.IMPL.mail(),
        Resources.IMPL.reddit(),
        Resources.IMPL.slashdot(),
        Resources.IMPL.twitter(),
        Resources.IMPL.yahoo()
    };
    SimplePanel holder = new SimplePanel();
    holder.add(new ImageRotator(icons));
    initWidget(holder);
  }
}
