package com.manning.gwtia.ch10.client.celltree;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.logging.client.ConsoleLogHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.TreeNode;
import com.google.gwt.uibinder.client.UiField;


import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch10.client.treeviewmodel.AsyncTreeViewModel;
import com.manning.gwtia.ch10.client.treeviewmodel.ListTreeViewModel;

public class CellTreeExample extends Composite {

	private static Logger rootLogger = Logger.getLogger("");

	interface CellTreeUiBinder extends UiBinder<Widget, CellTreeExample> {}	
	private static CellTreeUiBinder uiBinder = GWT.create(CellTreeUiBinder.class);
	
	@UiField(provided = true) CellTree photoList;
	
	public CellTreeExample() {
		
		rootLogger.addHandler(new ConsoleLogHandler());
			
		//createWithListTreeViewModel();
		createWithAsyncTreeViewModel();
		
		initWidget(uiBinder.createAndBindUi(this));
		this.setSize("100%", "100%");
		
		applyOptions();
	}
	
	
	private void applyOptions(){
		
		/**
		 * Set this to false to not have animation when opening nodes
		 */
		photoList.setAnimationEnabled(true);
		
		/**
		 * Un-comment the following line to see how paging works in CellTree
		 */
		//photoList.setDefaultNodeSize(10);		
	}
	
	
	private void createWithAsyncTreeViewModel(){
		AsyncTreeViewModel asyncModel;
		asyncModel = new AsyncTreeViewModel();
		photoList = new CellTree(asyncModel, null);		
	}
	
	
	private void createWithListTreeViewModel(){
		ListTreeViewModel listModel;
		listModel = new ListTreeViewModel();
		photoList = new CellTree(listModel, null);
	}

	
	@UiHandler("photoList")
	public void open(CloseEvent<TreeNode> event){
		String msg = "Node closed";
		rootLogger.log(Level.INFO,  msg);
	}
	
	
	@UiHandler("photoList")
	public void open(OpenEvent<TreeNode> event){
		String msg = "Node opened";
		rootLogger.log(Level.INFO,  msg);
	}
}
