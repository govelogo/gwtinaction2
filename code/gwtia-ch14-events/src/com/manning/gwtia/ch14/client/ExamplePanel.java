package com.manning.gwtia.ch14.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch14.client.defaultEventPreventing.PreventDefaultExample;
import com.manning.gwtia.ch14.client.eventEfficiency.EventEfficiencyExample;
import com.manning.gwtia.ch14.client.eventHandling.EventHandlingExample;
import com.manning.gwtia.ch14.client.eventPreviewing.EventPreviewingExample;
import com.manning.gwtia.ch14.client.eventSinking.EventSinkingExample;
import com.manning.gwtia.ch14.client.eventbus.EventBusExamplePanel;


public class ExamplePanel extends Composite
{
    private static ExamplePanelUiBinder uiBinder = GWT.create(ExamplePanelUiBinder.class);
    interface ExamplePanelUiBinder extends UiBinder<Widget, ExamplePanel> {}
    
    interface Resources extends ClientBundle {
        @Source("gwtia.jpg") public ImageResource logo();
    }
    
    @UiField Panel exampleArea;

    public ExamplePanel ()
    {
        initWidget(uiBinder.createAndBindUi(this));
        setWidgetToMaxWidthAndHeight();
        Window.addResizeHandler(resizeHandler);
        setWidgetAsExample(new IntroPanel());
    }

    
    EventHandlingExample eventHandlingExample;
    EventSinkingExample eventSinkingExample;
    EventEfficiencyExample eventEfficiencyExample;
    EventPreviewingExample eventPreviewingExample;
    PreventDefaultExample preventDefaultExample;
    EventBusExamplePanel eventBusExample;


    
    @UiHandler("showHandling")
    void showEventHandling (ClickEvent event)
    {
    	if (eventHandlingExample==null) eventHandlingExample = new EventHandlingExample();
        setWidgetAsExample(eventHandlingExample);
    }
    
    @UiHandler("sinkingEvent")
    void showSinkingExample (ClickEvent event)
    {
    	if (eventSinkingExample==null) eventSinkingExample = new EventSinkingExample();
        setWidgetAsExample(eventSinkingExample);
    }
    
    @UiHandler("eventEfficiency")
    void showEfficiencyExample (ClickEvent event)
    {
    	if (eventEfficiencyExample==null) eventEfficiencyExample = new EventEfficiencyExample();
        setWidgetAsExample(eventEfficiencyExample);
    }
    
    @UiHandler("showPreviewing")
    void showEventPreviewing (ClickEvent event)
    {
    	if (eventPreviewingExample==null) eventPreviewingExample = new EventPreviewingExample();
        setWidgetAsExample(eventPreviewingExample);
    }

    @UiHandler("preventDefault")
    void showPreviewDefault (ClickEvent event)
    {
    	if (preventDefaultExample==null) preventDefaultExample = new PreventDefaultExample();
        setWidgetAsExample(preventDefaultExample);
    }
    
    @UiHandler("eventBus")
    void showEventBus (ClickEvent event)
    {
    	if (eventBusExample==null) eventBusExample = new EventBusExamplePanel();
        setWidgetAsExample(eventBusExample);
    }
    
    @UiHandler("introPanel")
    void showInstructionsPanel (ClickEvent event)
    {
        setWidgetAsExample(new IntroPanel());
    }

    private void setWidgetAsExample (Widget widget)
    {
        exampleArea.clear();
        exampleArea.add(widget);
    }



    /* *************  WIDGET CENTERING CODE *************** */
    private ResizeHandler resizeHandler = new ResizeHandler()
    {
        public void onResize (ResizeEvent event)
        {
            setWidgetToMaxWidthAndHeight();
        }
    };



    private void setWidgetToMaxWidthAndHeight ()
    {
        setWidth("100%");
        setHeight("100%");
    }
}
