/**
 * 
 */
package com.manning.gwtia.ch11.client;


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
import com.manning.gwtia.ch11.client.api.APIExample;
import com.manning.gwtia.ch11.client.computestyle.ComputeStyleExample;
import com.manning.gwtia.ch11.client.intro.IntroPanel;
import com.manning.gwtia.ch11.client.javascriptobject.JSOverlayExample;
import com.manning.gwtia.ch11.client.jsonp.JSONPExample;
import com.manning.gwtia.ch11.client.photowall.PhotoWallExample;

/**
 * The template example panel, built for UiBinder instance (see Chapter X)
 * This is just a convenient way to display the frame for our examples; for the 
 * examples code, you should look in the relevant package - in this case
 * 
 * ch09.client.computestyle for the ComputeStyle example
 * ch09.client.jsoverlay for the JavaScript Overlay example
 * ch09.client.geolocate for the GeoLocation example
 * ch09.client.jsonp for the JSONP example
 * 
 */
public class ExamplePanel extends Composite
{
    private static ExamplePanelUiBinder uiBinder = GWT.create(ExamplePanelUiBinder.class);
    interface ExamplePanelUiBinder extends UiBinder<Widget, ExamplePanel> {}
    interface Resources extends ClientBundle {
        @Source("gwtia.jpg") public ImageResource logo();
    }
    
    
    private ComputeStyleExample computeStyleExample;
    private PhotoWallExample geolocateExample;
    private JSOverlayExample jsOverlayExample;
    private JSONPExample jsonpExample;
    private IntroPanel introPanel;
    private APIExample apiExample;

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
    
    
    @UiHandler("geolocate")
    public void showBookBar (ClickEvent event){
    	showBookBar();
    }

    public void showBookBar(){
    	if (geolocateExample==null) geolocateExample = new PhotoWallExample();
        setWidgetAsExample(geolocateExample);
    	History.newItem(HistoryTokens.GEOLOCATE);
    }
    
    @UiHandler("jsonp")
    public void showJsonp (ClickEvent event){
    	showJsonp();
    }

    public void showJsonp(){
    	if (jsonpExample==null) jsonpExample = new JSONPExample();
        setWidgetAsExample(jsonpExample);
    	History.newItem(HistoryTokens.JSON);
    }
    
    @UiHandler("overlay")
    public void showOverlay (ClickEvent event){
    	showOverlay();
    }

    public void showOverlay(){
    	if (jsOverlayExample==null) jsOverlayExample = new JSOverlayExample();
        setWidgetAsExample(jsOverlayExample);
    	History.newItem(HistoryTokens.OVERLAY);
    }
    
    
    @UiHandler("api")
    public void showAPI (ClickEvent event){
    	showAPI();
    }

    public void showAPI(){
    	if (apiExample==null) apiExample = new APIExample();
        setWidgetAsExample(apiExample);
    	History.newItem(HistoryTokens.API);
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
