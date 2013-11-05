package com.manning.gwtia.ch14.client.defaultEventPreventing;


import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class PreventDefaultExample extends ResizeComposite{
	
    private static EventPreviewingExampleUiBinder uiBinder = GWT.create(EventPreviewingExampleUiBinder.class);
    interface EventPreviewingExampleUiBinder extends UiBinder<Widget, PreventDefaultExample> {}
    
    
    @UiField FlowPanel comment; 
    @UiField ScrollPanel scroller;

	NativePreviewHandler keyboardShortcutHandler = new NativePreviewHandler(){
		@Override
		public void onPreviewNativeEvent(NativePreviewEvent event) {
			NativeEvent ne = event.getNativeEvent();
			if(event.getTypeInt()==Event.ONKEYPRESS||event.getTypeInt()==Event.ONKEYUP) return;
			if (ne.getCtrlKey()){
				String text="";
				if (ne.getKeyCode()=='s' || ne.getKeyCode()=='S'){
					event.cancel();
					ne.preventDefault();
					text = "Handling Ctrl+s";
				} else if (ne.getKeyCode()=='n' || ne.getKeyCode()=='N'){
					ne.preventDefault();
					text = "Handling Ctrl+n";
				}
				Label theEventLabel = new Label(text);
				comment.add(theEventLabel);
				scroller.ensureVisible(theEventLabel);
			}
		}
	};    
	
	
	public PreventDefaultExample(){
		initWidget(uiBinder.createAndBindUi(this));
        this.setSize("100%", "100%");
        Event.addNativePreviewHandler(keyboardShortcutHandler);
	}
	
	
    /**
     * When the clear button is pressed, we just simply clear the comment window.
     * @param event
     */
    @UiHandler("clear")
    void clearInformation (ClickEvent event){
    	comment.clear();
    }


}
