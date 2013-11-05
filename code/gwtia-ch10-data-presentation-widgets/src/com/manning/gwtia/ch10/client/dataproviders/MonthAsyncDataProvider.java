package com.manning.gwtia.ch10.client.dataproviders;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.manning.gwtia.ch10.shared.AsyncMonths;
import com.manning.gwtia.ch10.shared.PhotoAlbumService;
import com.manning.gwtia.ch10.shared.PhotoAlbumServiceAsync;


public class MonthAsyncDataProvider extends AsyncDataProvider<AsyncMonths> {

	private PhotoAlbumServiceAsync rpcService;
	
	int year;

	public MonthAsyncDataProvider(int year) {
		this.year = year;
		rpcService = GWT.create(PhotoAlbumService.class);
	}

	/**
	 * {@link #onRangeChanged(HasData)} is called when the table requests a new
	 * range of data. You can push data back to the displays using
	 * {@link #updateRowData(int, List)}.
	 */
	@Override
	protected void onRangeChanged(HasData<AsyncMonths> display) {
		// Get the new range.
		final Range range = display.getVisibleRange();

		rpcService.getMonthsList(year, range.getStart(), range.getLength(), new AsyncCallback<List<AsyncMonths>>() {
			public void onSuccess(List<AsyncMonths> result) {
				updateRowData(range.getStart(), result);
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error" + caught.getMessage());
			}
		});
	}
}
