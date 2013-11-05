package com.manning.gwtia.ch07.client;


import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenService;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.manning.gwtia.ch07.shared.FeedData;
import com.manning.gwtia.ch07.shared.TwitterService;
import com.manning.gwtia.ch07.shared.TwitterServiceAsync;

/**
 * Renders a simple interface for getting a specified user's Titter
 * history.  The interface has the user to type in the target
 * user's Twitter name into a text box and then clicking the "Get Tweets"
 * button will display the user's history on the page.
 */
public class GTwitter extends Composite
{
  
    /** Text box for entering in the Twitter user's name. */
    private TextBox txtScreenName = new TextBox();
  
    /** Button to activate the fetching of tweets. */
    private Button btnGetTweets = new Button("Get Tweets");
  
    /** Panel to display the tweets returned by the server-side service. */
    private VerticalPanel tweetPanel = new VerticalPanel();

  

    public GTwitter ()
    {
        FlowPanel rootPanel = new FlowPanel();
        rootPanel.add(txtScreenName);
        rootPanel.add(btnGetTweets);
        rootPanel.add(tweetPanel);
        initWidget(rootPanel);
        
        
        /*
         * An async callback coded as an anonymous class.  This can easily be moved into an inner
         * class or a separate class, making it easier to test as a separate unit.
         * 
         * This callback handles the results from a call to the getUserTimeline() method on the server.
         */
        final AsyncCallback<ArrayList<FeedData>> updateTweetPanelCallback = new AsyncCallback<ArrayList<FeedData>>() {

            /*
             * Displays an alert pop-up message when the service call returns an error.
             */
            public void onFailure (Throwable e)
            {
                Window.alert("Error: " + e.getMessage());
            }

            /*
             * Populates the tweetPanel with the tweets retrieved from the server. 
             */
            public void onSuccess (ArrayList<FeedData> results)
            {
                tweetPanel.clear();
                for (FeedData status : results) {
                    PredefinedFormat fmt = PredefinedFormat.TIME_SHORT;
                    String dateStr = DateTimeFormat.getFormat(fmt).format(status.getCreatedAt());
                    tweetPanel.add(new Label(dateStr + ": " + status.getText()));
                }
            }
        };

        /*
         * An click handler coded as an anonymous class.  This can easily be moved into an inner
         * class or a separate class, making it easier to test as a separate unit.
         * 
         * This method triggers a call to getUserTimeline() on the server when a user
         * clicks the btnGetTweets button.
         */
        btnGetTweets.addClickHandler(new ClickHandler()
        {
            public void onClick (ClickEvent event)
            {
                // Mentioned in the book as useful for testing if your server isn't sending
                // a JSESSIONID to the browser.
                //
                // if (Cookies.getCookie("JSESSIONID") == null)
                //    Cookies.setCookie("JSESSIONID", Double.toString(Math.random()));
                
                XsrfTokenServiceAsync xsrf = GWT.create(XsrfTokenService.class);
                ((ServiceDefTarget)xsrf).setServiceEntryPoint(GWT.getModuleBaseURL() + "xsrf");
                xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {

                    public void onSuccess (XsrfToken token)
                    {
                        TwitterServiceAsync service = GWT.create(TwitterService.class);
                        ((HasRpcToken) service).setRpcToken(token);
                        /*
                         * Alternative way to set the target URL on the server.  In most cases (as we do here),
                         * you would use the @RemoteServiceRelativePath annotation on the service interface.
                         */
                        // ((ServiceDefTarget) service).setServiceEntryPoint(GWT.getModuleBaseURL() + "service");
                        service.getUserTimeline(txtScreenName.getText(), updateTweetPanelCallback);
                    }

                    public void onFailure (Throwable caught)
                    {
                        try {
                            throw caught;
                        }
                        catch (RpcTokenException e) {
                            Window.alert("Error: " + e.getMessage());
                        }
                        catch (Throwable e) {
                            Window.alert("Error: " + e.getMessage());
                        }
                    }
                });                
            }
        });
    }

}
