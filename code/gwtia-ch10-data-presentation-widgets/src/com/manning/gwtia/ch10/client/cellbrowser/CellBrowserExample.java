package com.manning.gwtia.ch10.client.cellbrowser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.AbstractPager;
import com.google.gwt.user.cellview.client.CellBrowser;
import com.google.gwt.user.cellview.client.CellBrowser.Builder;
import com.google.gwt.user.cellview.client.CellBrowser.PagerFactory;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasRows;
import com.google.gwt.view.client.TreeViewModel;
import com.manning.gwtia.ch10.client.cellwidget.common.Years;
import com.manning.gwtia.ch10.client.treeviewmodel.AsyncTreeViewModel;
import com.manning.gwtia.ch10.client.treeviewmodel.ListTreeViewModel;

public class CellBrowserExample extends Composite {

	interface CellBrowserUiBinder extends UiBinder<Widget, CellBrowserExample> {}
	private static CellBrowserUiBinder uiBinder = GWT.create(CellBrowserUiBinder.class);

	
	final int PAGE_SIZE = 10;


	@UiField(provided = true) CellBrowser photoList;
	
	
	private void createWithListTreeViewModel(){
		TreeViewModel listModel = new ListTreeViewModel();
		cellBrowserBuilder = new Builder<Years>(listModel, null);
	}
	
	private void createWithAsyncTreeViewModel(){
		AsyncTreeViewModel asyncModel;
		asyncModel = new AsyncTreeViewModel();
		cellBrowserBuilder = new Builder<Years>(asyncModel, null);
	}
	
	Builder<Years> cellBrowserBuilder;
	
	private void applyBuilderOptions(){
		
		cellBrowserBuilder.pageSize(PAGE_SIZE);

		cellBrowserBuilder.pagerFactory(new PagerFactory(){
			@Override
			public AbstractPager create(HasRows display) {
				return new SimplePager();
			}	
		});
				
	}
	
	private void applyWidgetOptions(){
		photoList.setDefaultColumnWidth(300);
		photoList.setAnimationEnabled(true);
	}
	

	public CellBrowserExample() {

		createWithListTreeViewModel();
		//createWithAsyncTreeViewModel();
		
		applyBuilderOptions();

		photoList = cellBrowserBuilder.build();

		applyWidgetOptions();
		
		initWidget(uiBinder.createAndBindUi(this));
		this.setSize("100%", "100%");

	}
}
