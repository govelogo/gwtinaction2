package com.manning.gwtia.ch12.client;


import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class JsonpVideoPlaylist extends Composite
{
  private static Binder binder = GWT.create(Binder.class);
  
  @UiTemplate("VideoPlaylistX.ui.xml")
  interface Binder extends UiBinder<Widget, JsonpVideoPlaylist> {}

  @UiField Panel videos;

  public JsonpVideoPlaylist () {
    initWidget(binder.createAndBindUi(this));
    fetchAndDisplay();
  }
  
  public void fetchAndDisplay () {
    JsonpRequestBuilder rb = new JsonpRequestBuilder();

    String url = "http://gdata.youtube.com/feeds/api/videos?"
      + "author=DisturbedTV"
      + "&max-results=5"
      + "&orderby=published"
      + "&v=2&alt=json";
    
    rb.requestObject(url, new AsyncCallback<JavaScriptObject>() {
      @Override
      public void onFailure (Throwable caught) {
        videos.clear();
        videos.add(new HTML("ERROR: " + caught));
      }

      @Override
      public void onSuccess (JavaScriptObject jso) {
        videos.clear();
        extractAndDisplay(jso);
      }
    });
  }

  private void extractAndDisplay (JavaScriptObject jso) {
    JSONObject objResult = new JSONObject(jso);
    JSONObject feed = (JSONObject) objResult.get("feed");
    JSONArray entries = (JSONArray) feed.get("entry");
    for (int i = 0; i < entries.size(); i++) {
      JSONObject entry = (JSONObject) entries.get(i);
      JSONObject group = (JSONObject) entry.get("media$group");
      String title = ((JSONString) ((JSONObject) group.get("media$title")).get("$t")).stringValue();
      String description = ((JSONString) ((JSONObject) group.get("media$description")).get("$t")).stringValue();
      String thumbUrl = ((JSONString) ((JSONObject) ((JSONArray) group.get("media$thumbnail")).get(0)).get("url"))
          .stringValue();
      String link = ((JSONString) ((JSONObject) ((JSONArray) entry.get("link")).get(0)).get("href")).stringValue();

      videos.add(new VideoPlaylistItemX(thumbUrl, title, link, description));
    }
  }
  
}
