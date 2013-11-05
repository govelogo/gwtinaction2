package com.manning.gwtia.ch07.server;

import java.util.ArrayList;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.user.server.rpc.XsrfProtectedServiceServlet;
import com.manning.gwtia.ch07.shared.FeedData;
import com.manning.gwtia.ch07.shared.GTwitterException;
import com.manning.gwtia.ch07.shared.TwitterService;

/**
 * The server side implementation of the TwitterService interface.
 */
public class TwitterServiceImpl extends XsrfProtectedServiceServlet implements
    TwitterService
{

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -8892989521623692797L;

  
  /**
   * The main method can be used to test the output of the getUserTimeline()
   * method.  It calls the method and prints the results to the console.
   *
   * @param args the arguments (unused)
   * @throws Exception the exception
   */
  public static void main(String[] args) throws Exception {
    
    TwitterService impl = new TwitterServiceImpl();
    ArrayList<FeedData> resList = impl.getUserTimeline("ianchesnut");

    for (FeedData status : resList) {
      System.out.println(status.getCreatedAt() + " " + status.getText());
    }
  }

  /* (non-Javadoc)
   * @see com.gwtia.ch08.shared.TwitterService#getUserTimeline(java.lang.String)
   */
  public ArrayList<FeedData> getUserTimeline(String screenName)
    throws GTwitterException {
    
    Twitter twitter = new TwitterFactory().getInstance();
    ArrayList<FeedData> result = new ArrayList<FeedData>();

    try {
      ResponseList<Status> responses = twitter.getUserTimeline(screenName);
      for (Status status : responses) {
        result.add(new FeedData(status.getCreatedAt(), status.getText()));
      }
    } catch (TwitterException e) {
      throw new GTwitterException(e.getMessage());
    }

    return result;
  }
}
