package com.manning.gwtia.ch14.client.eventbus.widgets;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch14.client.event.ChangeColorEvent;
import com.manning.gwtia.ch14.client.eventbus.ColorEnum;

public class FireEventWidget extends Composite{
      Logger log = Logger.getLogger("");
	  
	  interface FireEventWidgetUiBinder extends UiBinder<Widget, FireEventWidget> {
	  }


	  private static FireEventWidgetUiBinder uiBinder = GWT.create(FireEventWidgetUiBinder.class);

	  private SimpleEventBus eventbus;

	  @UiField
	  FlowPanel containerPanel;

	  @UiField
	  Button changeColorButton;

	  @UiField
	  ListBox colorListBox;
	  


	  public FireEventWidget(SimpleEventBus eventBus) {
		    initWidget(uiBinder.createAndBindUi(this));

		    this.eventbus = eventBus;
		    colorListBox.addItem(ColorEnum.blue.getColor());
		    colorListBox.addItem(ColorEnum.green.getColor());
		    colorListBox.addItem(ColorEnum.red.getColor());
		    colorListBox.addItem(ColorEnum.yellow.getColor());
		    colorListBox.setVisibleItemCount(4);
		    

	  }


		@UiHandler("changeColorButton")
		void onClickChangeColor(ClickEvent e) {
			int selectedIndex=colorListBox.getSelectedIndex();
			String color=colorListBox.getItemText(selectedIndex);
		    this.eventbus.fireEvent(new ChangeColorEvent(color));

		}


	}

