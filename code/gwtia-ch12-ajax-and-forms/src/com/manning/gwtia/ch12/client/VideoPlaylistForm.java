package com.manning.gwtia.ch12.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.URL;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;

public class VideoPlaylistForm extends Composite
{
  private static Binder binder = GWT.create(Binder.class);
  interface Binder extends UiBinder<Widget, VideoPlaylistForm> {}

  @UiField FormPanel form;
  @UiField ListBox maxResults;
  @UiField XmlVideoPlaylist xmlPlaylist = new XmlVideoPlaylist();

  private SubmitCompleteHandler submitCompleteHandler = new SubmitCompleteHandler()
  {
    @Override
    public void onSubmitComplete (SubmitCompleteEvent event) {
      String xmlContent = URL.decodeComponent(event.getResults());
      try {
        xmlPlaylist.displayData(xmlContent);
      }
      catch (Exception caught) {
        xmlPlaylist.showError(caught);
      }
      xmlPlaylist.setVisible(true);
    }
  };


  public VideoPlaylistForm ()
  {
    initWidget(binder.createAndBindUi(this));
    
    maxResults.addItem("5");
    maxResults.addItem("10");
    maxResults.addItem("15");
    maxResults.setItemSelected(0, true);
    
    form.setAction(xmlPlaylist.getServiceUrl());
    form.addSubmitCompleteHandler(submitCompleteHandler);
  }
} 
