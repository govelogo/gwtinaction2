package com.manning.gwtia.ch12.client;

import com.google.gwt.core.client.JavaScriptObject;

public class VideoDataJso extends JavaScriptObject
{
  protected VideoDataJso () {}

  public final native String getTitle ()
  /*-{ return this.media$group.media$title.$t }-*/;

  public final native String getDesc ()
  /*-{ return this.media$group.media$description.$t }-*/;

  public final native String getImage ()
  /*-{ return this.media$group.media$thumbnail[0].url }-*/;

  public final native String getLink ()
  /*-{ return this.link[0].href }-*/;
}
