package com.manning.gwtia.ch10.client.celllist;

import java.util.List;

import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.PageSizePager;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.MultiSelectionModel;
import com.manning.gwtia.ch10.client.celltypes.custom.PhotoCellWithUiBinder;
import com.manning.gwtia.ch10.client.cellwidget.common.PsuedoDataSource;
import com.manning.gwtia.ch10.client.dataproviders.AllDataAsyncDataProvider;
import com.manning.gwtia.ch10.shared.PhotoDetails;

/**
 * GWT in Action 2nd Edition . example of a CellList widget.
 * 
 * Shows how to use a CellList widget using custom built PhotoDetails cells, as
 * well as application of 2 basic pagers.
 * 
 * You can alter page size by varying the PAGE_SIZE variable
 * 
 *
 */
public class CellListExamples extends Composite {

	interface CellTypesUiBinder extends UiBinder<Widget, CellListExamples> {
	}

	private static CellTypesUiBinder uiBinder = GWT
			.create(CellTypesUiBinder.class);

	AllDataAsyncDataProvider dataProviderAsync;

	ListDataProvider<PhotoDetails> dataProviderList;

	final int PAGE_SIZE = 6;

	@UiField
	SimplePager pager;
	@UiField
	PageSizePager pagerShowMore;

	// Display Cells
	@UiField(provided = true)
	CellList<PhotoDetails> photoList;

	public CellListExamples() {
		photoList = new CellList<PhotoDetails>(new PhotoCellWithUiBinder());
		initWidget(uiBinder.createAndBindUi(this));
		this.setSize("100%", "100%");

		applyOptions();
		setUpSelectionModel();
		pager.setDisplay(photoList);
		handleUpdates();

		// createWithAsyncDataProvider();
		createWithListDataProvider();

		photoList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		photoList.setKeyboardPagingPolicy(KeyboardPagingPolicy.CHANGE_PAGE);
	}

	private void applyOptions() {
		photoList.setPageSize(PAGE_SIZE);
	}

	private void createWithAsyncDataProvider() {
		dataProviderAsync = new AllDataAsyncDataProvider();
		dataProviderAsync.addDataDisplay(photoList);
		dataProviderAsync.updateRowCount(10, true);
	}

	private void createWithListDataProvider() {
		List<PhotoDetails> theList;

		dataProviderList = new ListDataProvider<PhotoDetails>();
		dataProviderList.addDataDisplay(photoList);
		theList = dataProviderList.getList();
		PsuedoDataSource.populate(theList, dataProviderList);
		// Keep the size of the list up to date - easier to know that we are
		// showing 51-100 of 2000 etc
		photoList.setRowCount(theList.size(), true);
	}

	private void handleUpdates() {
		photoList.setValueUpdater(new ValueUpdater<PhotoDetails>() {
			@Override
			public void update(PhotoDetails value) {
				Window.alert("Handling update on photo: " + value.getTitle());
			}
		});
	}

	private void setUpSelectionModel() {
		final MultiSelectionModel<PhotoDetails> selectionModel = new MultiSelectionModel<PhotoDetails>();
		photoList.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						if (selectionModel.getSelectedSet().size() > 5) {
							Window.alert("Cannot select more than 5 items");
							selectionModel.setSelected(selectionModel
									.getSelectedSet().iterator().next(), false);
						}
					}
				});
	}
}
