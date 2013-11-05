/**
 * 
 */
package com.manning.gwtia.ch04.client;

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
import com.manning.gwtia.ch04.client.layout.DockLayoutExample;
import com.manning.gwtia.ch04.client.layout.LayoutExample;
import com.manning.gwtia.ch04.client.layout.SplitLayoutExample;
import com.manning.gwtia.ch04.client.layout.StackLayoutExample;
import com.manning.gwtia.ch04.client.safehtml.SafeHtmlExample;
import com.manning.gwtia.ch04.client.animation.AnimationExample;
import com.manning.gwtia.ch04.client.build_composite_widget.CompositeExample;
import com.manning.gwtia.ch04.client.create_new_widget.CreateExample;
import com.manning.gwtia.ch04.client.create_new_widget.GWTCanvasWidgetExample;
import com.manning.gwtia.ch04.client.extend_existing_widget.ExtendExample;
import com.manning.gwtia.ch04.client.intro.IntroPanel;


/**
 * The template example panel, built for UiBinder instance (see Chapter X)
 * This is just a convenient way to display the frame for our examples; for the 
 * examples code, you should look in the relevant package - in this case
 * 
 * ch03.client.history for the History example
 * ch03.client.layout for the Layout example
 * 
 * (the ch03.client.intro package contains the introduction screen you see when the application loads)
 *
 */
public class ExamplePanel extends Composite
{
	/**
	 * UiBinder template code
	 */
    private static ExamplePanelUiBinder uiBinder = GWT.create(ExamplePanelUiBinder.class);
    interface ExamplePanelUiBinder extends UiBinder<Widget, ExamplePanel> {}
    
    /**
     * ClientBundle code for the GWT in Action logo shown on the main page.
     */
    interface Resources extends ClientBundle {
        @Source("gwtia.jpg") public ImageResource logo();
    }
    
    /**
     * The introPanel that displays introduction information.
     */
    private IntroPanel introPanel;
    /**
     * The HistoryExample widget that shows how history is handled in a GWT application
     */
    private CreateExample createExample;
    /**
     * The LayoutExample widget that shows how GWT can use Layout Panels for screen rendering
     */
    private ExtendExample extendExample;
    /**
     * The LayoutExample widget that shows how GWT can use Layout Panels for screen rendering
     */
    private CompositeExample compositeExample;
    
    /**
     * The Layout Example widgets that shows how GWT can use Layout Panels for screen rendering
     */
    private LayoutExample layoutExample;
    private SplitLayoutExample splitLayoutExample;
    private DockLayoutExample dockLayoutExample;
    private StackLayoutExample stackLayoutExample;
    private AnimationExample animationExample;
    private SafeHtmlExample safeHtmlExample;

    
    /**
     * The Panel in the main screen where the examples will be displayed.
     */
    @UiField Panel exampleArea;
	private GWTCanvasWidgetExample createGWTExample;


    /**
     * The EntryPoint.
     * Binds the UiBinder code and creates the widget to be shown.
     * Adds a ResizeHandler to manage and resize events of the browser that are created
     * Sets up the IntroPanel as the first widget shown to the user.
     */
    public ExamplePanel (){
        initWidget(uiBinder.createAndBindUi(this));
        setWidgetToMaxWidthAndHeight();
        Window.addResizeHandler(resizeHandler);
        introPanel = new IntroPanel();
        setWidgetAsExample(introPanel);
    }

    /**
     * If the user clicks on the create example button, show the HistoryPanel.
     */
    @UiHandler("create")
    public void showCreate (ClickEvent event){
    	History.newItem(HistoryTokens.CREATE);
    }

    /**
     * Helper method for showing history panel.
     * Called when the history button is clicked in this widget, or if the user initiates it via
     * a history token in the URL (clicks back/forward, or arrives at http://.......Example.html#history
     */
    public void showCreate(){
    	if (createExample==null) createExample = new CreateExample();
        setWidgetAsExample(createExample);
        
    	// Comment out the above and uncomment below if you want to see the provided GWT Canvas widget instead.
//        if (createGWTExample==null) createGWTExample = new GWTCanvasWidgetExample();  	
//        setWidgetAsExample(createGWTExample);
    }

    /**
     * If the user clicks on the layout example button, show the HistoryPanel.
     */    
    @UiHandler("extend")
    public void showExtend (ClickEvent event){
    	History.newItem(HistoryTokens.EXTEND);
    }

    /**
     * Helper method for showing layout panel.
     * Called when the layout button is clicked in this widget, or if the user initiates it via
     * a history token in the URL (clicks back/forward, or arrives at http://.......Example.html#layout
     */
    public void showExtend()
    {
    	if (extendExample==null) extendExample = new ExtendExample();
        setWidgetAsExample(extendExample);
    }
    
    
    /**
     * If the user clicks on the layout example button, show the HistoryPanel.
     */    
    @UiHandler("composite")
    public void showComposite (ClickEvent event){
    	History.newItem(HistoryTokens.COMPOSITE);
    }

    /**
     * Helper method for showing layout panel.
     * Called when the layout button is clicked in this widget, or if the user initiates it via
     * a history token in the URL (clicks back/forward, or arrives at http://.......Example.html#layout
     */
    public void showComposite(){
    	if (compositeExample==null) compositeExample = new CompositeExample();
        setWidgetAsExample(compositeExample);
    }

    /**
     * If the user clicks on the layout example button, show the HistoryPanel.
     */    
    @UiHandler("safehtml")
    public void showSafeHtml (ClickEvent event){
    	History.newItem(HistoryTokens.SAFEHTML);
    }

    /**
     * Helper method for showing layout panel.
     * Called when the layout button is clicked in this widget, or if the user initiates it via
     * a history token in the URL (clicks back/forward, or arrives at http://.......Example.html#layout
     */
    public void showSafeHtml(){
    	if (safeHtmlExample==null) safeHtmlExample = new SafeHtmlExample();
        setWidgetAsExample(safeHtmlExample);
    }

    /**
     * Helper method for showing introduction panel.
     * Called when the intro button is clicked in this widget, or if the user initiates it via
     * lack of a history token in the URL (clicks back/forward, or arrives at http://.......Example.html
     */
    public void showInstructionsPanel(){
        setWidgetAsExample(introPanel);
    }
    
    
    @UiHandler("introPanel")
    void showInstructionsPanel (ClickEvent event){
    	History.newItem(HistoryTokens.INTRODUCTION);
    }
    
    /**
     * Helper method for showing animation example.
     * Called when the animation button is clicked in this widget, or if the user initiates it via
     * lack of a history token in the URL (clicks back/forward, or arrives at http://.......Example.html
     */
    public void showAnimationPanel(){
    	if (animationExample==null) animationExample = new AnimationExample();
        setWidgetAsExample(animationExample);
    }
    
    
    @UiHandler("animation")
    void showAnimationPanel (ClickEvent event){
    	History.newItem(HistoryTokens.ANIMATION);
    }

    
    /**
     * If the user clicks on the layout example button, show the HistoryPanel.
     */    
    @UiHandler("layout")
    public void showLayout (ClickEvent event)
    {
    	History.newItem(HistoryTokens.LAYOUT);
    }

    /**
     * Helper method for showing layout panel.
     * Called when the layout button is clicked in this widget, or if the user initiates it via
     * a history token in the URL (clicks back/forward, or arrives at http://.......Example.html#layout
     */
    public void showLayout()
    {
    	if (layoutExample==null) layoutExample = new LayoutExample();
        setWidgetAsExample(layoutExample);
    }
    
    
    /**
     * If the user clicks on the layout example button, show the HistoryPanel.
     */    
    @UiHandler("splitlayout")
    public void showSplitLayout (ClickEvent event)
    {
    	History.newItem(HistoryTokens.SPLITLAYOUT);
    }

    /**
     * Helper method for showing layout panel.
     * Called when the layout button is clicked in this widget, or if the user initiates it via
     * a history token in the URL (clicks back/forward, or arrives at http://.......Example.html#layout
     */
    public void showSplitLayout()
    {
    	if (splitLayoutExample==null) splitLayoutExample = new SplitLayoutExample();
        setWidgetAsExample(splitLayoutExample);
    }
    
    
    /**
     * If the user clicks on the layout example button, show the HistoryPanel.
     */    
    @UiHandler("stacklayout")
    public void showStackLayout (ClickEvent event)
    {
    	History.newItem(HistoryTokens.STACKLAYOUT);
    }

    /**
     * Helper method for showing layout panel.
     * Called when the layout button is clicked in this widget, or if the user initiates it via
     * a history token in the URL (clicks back/forward, or arrives at http://.......Example.html#layout
     */
    public void showStackLayout()
    {
    	if (stackLayoutExample==null) stackLayoutExample = new StackLayoutExample();
        setWidgetAsExample(stackLayoutExample);
    }
    
    /**
     * If the user clicks on the layout example button, show the HistoryPanel.
     */    
    @UiHandler("docklayout")
    public void showDockLayout (ClickEvent event)
    {
        //showDockLayout();
    	History.newItem(HistoryTokens.DOCKLAYOUT);
    }

    /**
     * Helper method for showing layout panel.
     * Called when the layout button is clicked in this widget, or if the user initiates it via
     * a history token in the URL (clicks back/forward, or arrives at http://.......Example.html#layout
     */
    public void showDockLayout()
    {
    	if (dockLayoutExample==null) dockLayoutExample = new DockLayoutExample();
        setWidgetAsExample(dockLayoutExample);
    }
    

    
    /**
     * Simple method to set a widget in the example area and clear out any previous widget.
     * @param widget The new widget to show as the example.
     */
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
