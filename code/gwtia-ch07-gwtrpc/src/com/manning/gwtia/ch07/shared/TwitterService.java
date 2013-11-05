package com.manning.gwtia.ch07.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The service interface for the GTwitter server-side service.
 */
@RemoteServiceRelativePath("service")
public interface TwitterService extends RemoteService
{
  
  /**
   * Gets the user timeline.
   *
   * @param screenName the screen name
   * @return the user timeline
   * @throws GTwitterException the g twitter exception
   */
  ArrayList<FeedData> getUserTimeline(String screenName)
      throws GTwitterException;
}