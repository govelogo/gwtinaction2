package com.manning.gwtia.ch12.client;

import com.google.gwt.core.client.JavaScriptObject;

public class VideoFeedJso extends JavaScriptObject
{
  protected VideoFeedJso () { }

  public final native int entryCount ()
  /*-{ return this.feed.entry.length }-*/;

  public final native VideoDataJso getEntry (int index)
  /*-{ return this.feed.entry[index] }-*/;
}
