package com.manning.gwtia.ch13.client;

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
import com.manning.gwtia.ch13.client.direction.DirectionExample;
import com.manning.gwtia.ch13.client.i18ndynamic.DynamicExample;
import com.manning.gwtia.ch13.client.i18nstatic.StaticExample;
import com.manning.gwtia.ch13.client.intro.IntroPanel;


public class ExamplePanel extends Composite
{
    interface ExamplePanelUiBinder extends UiBinder<Widget, ExamplePanel> {}
    interface Resources extends ClientBundle {
        @Source("gwtia.jpg") public ImageResource logo();
    }
    private static ExamplePanelUiBinder uiBinder = GWT.create(ExamplePanelUiBinder.class);
    
    private DirectionExample directionExample;
    private DynamicExample dynamicExample;
    @UiField Panel exampleArea;
    private IntroPanel introPanel;


    /* *************  WIDGET CENTERING CODE *************** */
    private ResizeHandler resizeHandler = new ResizeHandler()
    {
        public void onResize (ResizeEvent event)
        {
            setWidgetToMaxWidthAndHeight();
        }
    };


    private StaticExample staticExample;

    public ExamplePanel ()
    {
        initWidget(uiBinder.createAndBindUi(this));
        setWidgetToMaxWidthAndHeight();
        Window.addResizeHandler(resizeHandler);
        introPanel = new IntroPanel();
        setWidgetAsExample(introPanel);
    }

    private void setWidgetAsExample (Widget widget)
    {
        exampleArea.clear();
        exampleArea.add(widget);
    }

    
    private void setWidgetToMaxWidthAndHeight ()
    {
        setWidth("100%");
        setHeight(Window.getClientHeight() + "px");
    }

    public void showDirectionI18n()
    {
    	if (directionExample==null) directionExample = new DirectionExample();
        setWidgetAsExample(directionExample);
    	History.newItem(HistoryTokens.I18NDIRECTION);
    }
    
    @UiHandler("directionI18N")
    public void showDirectionI18n (ClickEvent event)
    {
        showDirectionI18n();
    }

    public void showDynamicI18n()
    {
    	if (dynamicExample==null) dynamicExample = new DynamicExample();
        setWidgetAsExample(dynamicExample);
    	History.newItem(HistoryTokens.I18NDYNAMIC);
    }
    
        
    @UiHandler("dynamicI18N")
    public void showDynamicI18n (ClickEvent event)
    {
        showDynamicI18n();
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
    



    public void showStaticI18n(){
    	if (staticExample==null) staticExample = new StaticExample();
        setWidgetAsExample(staticExample);
    	History.newItem(HistoryTokens.I18NSTATIC);
    }



    @UiHandler("staticI18N")
    public void showStaticI18n (ClickEvent event){
    	showStaticI18n();
    }
}
