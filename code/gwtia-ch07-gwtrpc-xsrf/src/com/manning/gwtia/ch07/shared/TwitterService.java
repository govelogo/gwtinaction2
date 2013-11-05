package com.manning.gwtia.ch07.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.XsrfProtectedService;

/**
 * The service interface for the GTwitter server-side service.
 */
@RemoteServiceRelativePath("service")
public interface TwitterService extends XsrfProtectedService
{
  
  /**
   * Gets the user time line.
   *
   * @param screenName the screen name
   * @return the user time line
   * @throws GTwitterException
   */
    ArrayList<FeedData> getUserTimeline(String screenName)
      throws GTwitterException;
}