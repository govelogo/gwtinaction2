package com.manning.gwtia.ch18.client;

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
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch18.client.generator.GeneratorExample;
import com.manning.gwtia.ch18.client.intro.IntroPanel;


public class ExamplePanel extends Composite
{
    private static ExamplePanelUiBinder uiBinder = GWT.create(ExamplePanelUiBinder.class);
    interface ExamplePanelUiBinder extends UiBinder<Widget, ExamplePanel> {}
    interface Resources extends ClientBundle {
        @Source("gwtia.jpg") public ImageResource logo();
    }
    
    private IntroPanel introPanel;
    private GeneratorExample generatorExample;

    @UiField Panel exampleArea;


    public ExamplePanel ()
    {
        initWidget(uiBinder.createAndBindUi(this));
        setWidgetToMaxWidthAndHeight();
        Window.addResizeHandler(resizeHandler);
        introPanel = new IntroPanel();
        setWidgetAsExample(introPanel);
    }

    @UiHandler("generator")
    public void showGenerator (ClickEvent event){
    	showGenerator();
    }

    public void showGenerator(){
    	if (generatorExample==null) generatorExample = new GeneratorExample();
        setWidgetAsExample(generatorExample);
    	History.newItem(HistoryTokens.GENERATOR);
    }

        public void showInstructionsPanel()
    {
        setWidgetAsExample(introPanel);
    	History.newItem(HistoryTokens.INTRODUCTION);
    }
    
    
    @UiHandler("introPanel")
    void showInstructionsPanel (ClickEvent event)
    {
    	showInstructionsPanel();
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
