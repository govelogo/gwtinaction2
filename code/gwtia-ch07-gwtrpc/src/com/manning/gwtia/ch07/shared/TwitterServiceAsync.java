package com.manning.gwtia.ch07.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The asynchronous interface that is a counterpart to the TwitterService
 * server-side interface.  The async interface is used by the client-side
 * to call the server-side service.
 */
public interface TwitterServiceAsync
{
  
  /**
   * Gets the user timeline.
   *
   * @param screenName the screen name
   * @param callback the callback
   * @return the user timeline
   */
  void getUserTimeline(String screenName,
      AsyncCallback<ArrayList<FeedData>> callback);
}
