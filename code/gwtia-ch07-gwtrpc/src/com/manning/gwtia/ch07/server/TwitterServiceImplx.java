package com.manning.gwtia.ch07.server;


import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * An implementation of getUserTimeline that is NOT GWT-RPC compatible.
 * This is just a demonstration of how you would code the server-side
 * method without considering GWT-RPC.  The chapter uses this as a base
 * to develop a GWT-RPC compatible version of this same method.
 */
public class TwitterServiceImplx
{    
    
    /**
     * A test method that calls the getUserTimeline() method and prints
     * the tweets for the hard-coded user to the console.
     *
     * @param args the arguments (unused)
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception
    {
        TwitterServiceImplx impl = new TwitterServiceImplx();
        ResponseList<Status> resList = impl.getUserTimeline("ianchesnut");
        
        for (Status status : resList) {
            System.out.println(status.getCreatedAt() + ": " + status.getText());
        }
    }
    
    /**
     * Gets the timeline for a specified Twitter user.
     *
     * @param screenName the screen name
     * @return the user timeline
     * @throws TwitterException the twitter exception
     */
    public ResponseList<Status> getUserTimeline (String screenName) throws TwitterException
    {
        Twitter twitter = new TwitterFactory().getInstance();
        return twitter.getUserTimeline(screenName);
    }
}
