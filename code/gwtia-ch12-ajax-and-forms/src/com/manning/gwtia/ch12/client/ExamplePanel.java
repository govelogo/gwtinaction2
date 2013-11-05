package com.manning.gwtia.ch12.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class ExamplePanel extends Composite
{
  private static ExamplePanelUiBinder uiBinder = GWT.create(ExamplePanelUiBinder.class);
  interface ExamplePanelUiBinder extends UiBinder<Widget, ExamplePanel>{}

  @UiField Panel exampleArea;
  
  
  public ExamplePanel() {
    initWidget(uiBinder.createAndBindUi(this));
    
    setWidgetToMaxWidthAndHeight();
    Window.addResizeHandler(resizeHandler);
    
    setWidgetAsExample(new IntroPanel());
  }


  @UiHandler("introPanel")
  void showInstructionsPanel(ClickEvent event) {
    setWidgetAsExample(new IntroPanel());
  }

  @UiHandler("jsonp")
  void showJsonpPanel (ClickEvent event) {
    setWidgetAsExample(new JsonpVideoPlaylist());
  }

  @UiHandler("jsoverlay")
  void showJsOverlayPanel (ClickEvent event) {
    setWidgetAsExample(new JsOverlayVideoPlaylist());
  }

  @UiHandler("xml")
  void showXmlPanel (ClickEvent event) {
    XmlVideoPlaylist videoPlaylist = new XmlVideoPlaylist();
    videoPlaylist.searchAndDisplay("APB", 5);
    setWidgetAsExample(videoPlaylist);
  }

  @UiHandler("xmlform")
  void showXmlFormPanel (ClickEvent event) {
    setWidgetAsExample(new VideoPlaylistForm());
  }

  private void setWidgetAsExample(Widget widget) {
    exampleArea.clear();
    exampleArea.add(widget);
  }
  
  
  
  /* *************  WIDGET CENTERING CODE *************** */

  private ResizeHandler resizeHandler = new ResizeHandler() {
    public void onResize(ResizeEvent event) {
      setWidgetToMaxWidthAndHeight();
    }
  };

  private void setWidgetToMaxWidthAndHeight() {
    setWidth("100%");
    setHeight(Window.getClientHeight() + "px");
  }

}
