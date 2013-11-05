package com.manning.gwtia.ch05.client.imageresource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;

public interface Resources extends StaticClientBundle {
  Resources IMPL = (Resources) GWT.create(Resources.class);
  
  ImageResource blogger();
  ImageResource delicious();
  ImageResource digg();
  ImageResource facebook();
  ImageResource google();
  @ImageOptions(preventInlining=true)
  ImageResource mail();
  @ImageOptions(preventInlining=true)
  ImageResource reddit();
  @ImageOptions(preventInlining=true)
  ImageResource slashdot();
  ImageResource twitter();
  ImageResource yahoo();
}
