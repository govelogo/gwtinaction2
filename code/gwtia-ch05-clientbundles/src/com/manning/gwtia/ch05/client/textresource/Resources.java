package com.manning.gwtia.ch05.client.textresource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.TextResource;

public interface Resources extends ClientBundle {
  Resources IMPL = (Resources) GWT.create(Resources.class);
  
  @Source("text.txt")
  TextResource text();
  
  @Source("ext_text.txt")
  ExternalTextResource extText();
}
