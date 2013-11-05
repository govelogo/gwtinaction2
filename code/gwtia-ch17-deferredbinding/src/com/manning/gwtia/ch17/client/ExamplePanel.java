package com.manning.gwtia.ch17.client;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch17.client.audio.AudioExample;
import com.manning.gwtia.ch17.client.computestyle.ComputeStyleExample;
import com.manning.gwtia.ch17.client.weekend.PropertyExample;


public class ExamplePanel extends Composite
{
    private static ExamplePanelUiBinder uiBinder = GWT.create(ExamplePanelUiBinder.class);
    interface ExamplePanelUiBinder extends UiBinder<Widget, ExamplePanel> {}
    interface Resources extends ClientBundle {
        @Source("gwtia.jpg") public ImageResource logo();
    }
    
    
    private ComputeStyleExample computeStyleExample;
    private PropertyExample propertyExample;
    private AudioExample audioExample;
    private IntroPanel introPanel;

    @UiField SimplePanel exampleArea;

    public ExamplePanel ()
    {
        initWidget(uiBinder.createAndBindUi(this));
        setWidgetToMaxWidthAndHeight();
        Window.addResizeHandler(resizeHandler);
        introPanel = new IntroPanel();
        setWidgetAsExample(introPanel);
    }

    @UiHandler("computeStyle")
    public void showComputeStyle (ClickEvent event){
    	showComputeStyle();
    }

    public void showComputeStyle(){
    	if (computeStyleExample==null) computeStyleExample = new ComputeStyleExample();
        setWidgetAsExample(computeStyleExample);
    	History.newItem(HistoryTokens.COMPUTESTYLE);
    }
    
    
    @UiHandler("weekendExample")
    public void showPropertyExample (ClickEvent event){
    	showPropertyExample();
    }

    public void showPropertyExample(){
    	if (propertyExample==null) propertyExample = new PropertyExample();
        setWidgetAsExample(propertyExample);
    	History.newItem(HistoryTokens.PROPERTY);
    }
    
    @UiHandler("audio")
    public void showAudioExample (ClickEvent event){
    	showAudioExample();
    }

    public void showAudioExample(){
    	if (audioExample==null) audioExample = new AudioExample();
        setWidgetAsExample(audioExample);
    	History.newItem(HistoryTokens.AUDIO);
    }
    
    
    @UiHandler("introPanel")
    void showInstructionsPanel (ClickEvent event)
    {
    	showInstructionsPanel();
    }
    

    public void showInstructionsPanel()
    {
        setWidgetAsExample(introPanel);
    	History.newItem(HistoryTokens.INTRODUCTION);
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
        setHeight(Window.getClientHeight() + "px");
    }
}
