package com.manning.gwtia.ch10.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
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
import com.manning.gwtia.ch10.client.cellbrowser.CellBrowserExample;
import com.manning.gwtia.ch10.client.celllist.CellListExamples;
import com.manning.gwtia.ch10.client.celltable.CellTableExample;
import com.manning.gwtia.ch10.client.celltree.CellTreeExample;
import com.manning.gwtia.ch10.client.celltypes.CellTypesExample;
import com.manning.gwtia.ch10.client.datagrid.CellDataGridExample;


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

    CellTypesExample cellTypesExample;
    CellListExamples cellListExample;
    CellTreeExample cellTreeExample;
    CellBrowserExample cellBrowserExample;
    CellTableExample cellTableExample;
    CellDataGridExample cellDataGridExample;
    
    @UiHandler("cells")
    void showLoginExample_v1 (ClickEvent event)
    {
    	GWT.runAsync(new RunAsyncCallback(){

			@Override
			public void onFailure(Throwable reason) {				
			}

			@Override
			public void onSuccess() {
		    	if (cellTypesExample==null) cellTypesExample = new CellTypesExample();
		        setWidgetAsExample(cellTypesExample);
		        }
    	});
    }
    
    @UiHandler("cellList")
    void showCellList (ClickEvent event)
    {
    	GWT.runAsync(new RunAsyncCallback(){
			@Override
			public void onFailure(Throwable reason) {
			}

			@Override
			public void onSuccess() {
		    	if (cellListExample==null) cellListExample = new CellListExamples();
		        setWidgetAsExample(cellListExample);	
			}    		
    	});
    }
    
    @UiHandler("cellTree")
    void showCellTree (ClickEvent event)
    {
    	GWT.runAsync(new RunAsyncCallback(){
			@Override
			public void onFailure(Throwable reason) {
			}

			@Override
			public void onSuccess() {
		    	if (cellTreeExample==null) cellTreeExample = new CellTreeExample();
		        setWidgetAsExample(cellTreeExample);				
			}
    	});
    }
    
    @UiHandler("cellBrowser")
    void showCellBrowser (ClickEvent event)
    {
    	GWT.runAsync(new RunAsyncCallback(){
			@Override
			public void onFailure(Throwable reason) {
			}

			@Override
			public void onSuccess() {
		    	if (cellBrowserExample==null) cellBrowserExample = new CellBrowserExample();
		        setWidgetAsExample(cellBrowserExample);				
			}
    		
    	});
    }
    
    @UiHandler("cellTable")
    void showCellTable (ClickEvent event)
    {
    	GWT.runAsync(new RunAsyncCallback(){
			@Override
			public void onFailure(Throwable reason) {
			}

			@Override
			public void onSuccess() {
		    	if (cellTableExample==null) cellTableExample = new CellTableExample();
		        setWidgetAsExample(cellTableExample);				
			}
    	});
    }
    
    @UiHandler("dataGrid")
    void showCellDataGridTable (ClickEvent event)
    {
    	GWT.runAsync(new RunAsyncCallback(){

			@Override
			public void onFailure(Throwable reason) {
			}

			@Override
			public void onSuccess() {
		    	if (cellDataGridExample==null) cellDataGridExample = new CellDataGridExample();
		        setWidgetAsExample(cellDataGridExample);				
			}
    	});
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
