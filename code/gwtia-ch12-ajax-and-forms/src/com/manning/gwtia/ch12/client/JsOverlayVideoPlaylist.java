package com.manning.gwtia.ch12.client;


import com.google.gwt.core.client.GWT;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class JsOverlayVideoPlaylist extends Composite
{
  private static Binder binder = GWT.create(Binder.class);
  @UiTemplate("VideoPlaylistX.ui.xml")
  interface Binder extends UiBinder<Widget, JsOverlayVideoPlaylist> {}

  @UiField Panel videos;

  public JsOverlayVideoPlaylist () {
    initWidget(binder.createAndBindUi(this));
    fetchAndDisplay();
  }

  
  public void fetchAndDisplay () {
    JsonpRequestBuilder rb = new JsonpRequestBuilder();

    String url = "http://gdata.youtube.com/feeds/api/videos?" 
      + "q=Eve+Online+gameplay"
      + "&max-results=5"
      + "&uploaded=w"
      + "&v=2&alt=json";
    
    rb.requestObject(url, new AsyncCallback<VideoFeedJso>() {
      @Override
      public void onFailure (Throwable caught) {
        videos.clear();
        videos.add(new HTML("ERROR: " + caught));
      }

      @Override
      public void onSuccess (VideoFeedJso feedJso) {
        videos.clear();
        for (int i = 0; i < feedJso.entryCount(); i++) {
          VideoDataJso data = feedJso.getEntry(i);
          videos.add(new VideoPlaylistItemX(data.getImage(), data.getTitle(), data.getLink(), data.getDesc()));
        }
      }
    });
  }
  
}
