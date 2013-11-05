package com.manning.gwtia.ch14.client.eventbus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch14.client.eventbus.widgets.EventSubscriberWidget1;
import com.manning.gwtia.ch14.client.eventbus.widgets.EventSubscriberWidget2;
import com.manning.gwtia.ch14.client.eventbus.widgets.FireEventWidget;

public class EventBusExamplePanel extends Composite
{
  interface EventBusExampleUiBinder extends UiBinder<Widget, EventBusExamplePanel> {}

  private static EventBusExampleUiBinder uiBinder = GWT.create(EventBusExampleUiBinder.class);

  interface Resources extends ClientBundle {
      @Source("EventBus.png") public ImageResource eventbus();
  }

 
  @UiField
  FlowPanel subscriber1Panel;

  @UiField
  FlowPanel subscriber2Panel;

  @UiField
  FlowPanel publisherPanel;
 
  private EventSubscriberWidget1 widget1Panel;

  
  private EventSubscriberWidget2 widget2Panel;
  
  
  private FireEventWidget fireEventWidget;

  public EventBusExamplePanel() {
    initWidget(uiBinder.createAndBindUi(this));
    
    SimpleEventBus eventBus= new SimpleEventBus();
    
    widget1Panel = new EventSubscriberWidget1(eventBus);
    
    DOM.setStyleAttribute(widget1Panel.getElement(),
            "border", "1px solid #00f");
    DOM.setStyleAttribute(widget1Panel.getElement(),
            "backgroundColor", "yellow");
    widget1Panel.setSize("223px", "203px");

    
    widget2Panel = new EventSubscriberWidget2(eventBus);
    
    DOM.setStyleAttribute(widget2Panel.getElement(),
            "border", "1px solid #00f");
    DOM.setStyleAttribute(widget2Panel.getElement(),
            "backgroundColor", "yellow");
    widget2Panel.setSize("223px", "203px");

    
    fireEventWidget = new FireEventWidget(eventBus);
    
    subscriber1Panel.add(widget1Panel);
    subscriber2Panel.add(widget2Panel);
    publisherPanel.add(fireEventWidget);
    
    this.setSize("447px", "203px");

  }

}
