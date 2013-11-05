package com.manning.gwtia.ch12.client;


import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

public class XmlVideoPlaylist extends Composite
{
  private static Binder binder = GWT.create(Binder.class);
  @UiTemplate("VideoPlaylistX.ui.xml")
  interface Binder extends UiBinder<Widget, XmlVideoPlaylist> {}

  @UiField Panel videos;

  public XmlVideoPlaylist () {
    initWidget(binder.createAndBindUi(this));
  }

  public void searchAndDisplay (String query, int maxResults) {
    getVideosForSearch(query, maxResults);
  }

  public native String getServiceUrl ()
  /*-{
    if ($wnd.xmlServiceUrl) {
      return $wnd.xmlServiceUrl;
    }
    return "xml.proxy";
  }-*/;

  private void getVideosForSearch (String query, int maxResults) {
    String queryString = "?query=" + URL.encode(query)+ "&maxResults=" + maxResults;
    RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, getServiceUrl() + queryString);

    rb.setCallback(new RequestCallback()
    {
      @Override
      public void onResponseReceived (Request request, Response response)
      {
        try {
          videos.clear();
          displayData(response.getText());
        }
        catch (Exception caught) {
          showError(caught);
        }
      }
      
      @Override
      public void onError (Request request, Throwable caught)
      {
        showError(caught);
      }
    });
    
    try {
      rb.send();
    }
    catch (RequestException caught) {
      showError(caught);
    }
  }

  public void showError (Throwable caught) {
    showError(caught);
  }

  public void displayData (String xmlContent) throws Exception
  {
    videos.clear();
    Document doc = XMLParser.parse(xmlContent);
    NodeList nlEntries = doc.getElementsByTagName("entry");
    
    for (int i = 0; i < nlEntries.getLength(); i++) {
      Element eEntry = (Element) nlEntries.item(i);
      Element eGroup = (Element) eEntry.getElementsByTagName("group").item(0);
      
      Element eLink = (Element) eEntry.getElementsByTagName("link").item(0);
      String link = eLink.getAttribute("href");
      
      Element eTitle = (Element) eGroup.getElementsByTagName("title").item(0);
      String title = extractText(eTitle);
      
      Element eDesc = (Element) eGroup.getElementsByTagName("description").item(0);
      String desc = extractText(eDesc);
      
      Element eThumb = (Element) eGroup.getElementsByTagName("thumbnail").item(0);
      String thumbUrl = eThumb.getAttribute("url");
      
      videos.add(new VideoPlaylistItemX(thumbUrl, title, link, desc));
    }
  }
  
  private String extractText (Node node)
  {
    String result = "";
    NodeList children = node.getChildNodes();
    for (int x = 0; x < children.getLength(); x++) {
      Node child = children.item(x);
      if (child.getNodeType() == Node.TEXT_NODE) {
        result += children.item(x).getNodeValue();
      }
      else if (child.getNodeType() == Node.CDATA_SECTION_NODE) {
        result += children.item(x).getNodeValue();
      }
      else if (child.hasChildNodes()) {
        result += extractText(children.item(x));
      }
    }
    return result;
  }
  
}
