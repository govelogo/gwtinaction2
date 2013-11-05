package com.manning.gwtia.ch14.client.eventbus.widgets;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch14.client.event.ChangeColorEvent;
import com.manning.gwtia.ch14.client.event.ChangeColorEventHandler;
import com.manning.gwtia.ch14.client.eventbus.ColorEnum;

public class EventSubscriberWidget2 extends Composite{
      Logger log = Logger.getLogger("");

	  
	  interface Binder extends UiBinder<Widget, EventSubscriberWidget2> {
	  }


	  @UiField
	  FlowPanel container;
	  


	  private SimpleEventBus eventbus;
  

		public EventSubscriberWidget2(SimpleEventBus eventbus) {

		    this.eventbus = eventbus;

		    initWidget(GWT.<Binder> create(Binder.class).createAndBindUi(this));
		    eventbus.addHandler(ChangeColorEvent.TYPE, new ChangeColorEventHandler() {
		    	

				@Override
				public void onChangeColorSent(ChangeColorEvent event) {
					ColorEnum colorEnum= ColorEnum.valueOf(event.getColor());
					container.getElement().getStyle().setProperty("backgroundColor", colorEnum.getColor());
				}
		    	});

		}




	}

