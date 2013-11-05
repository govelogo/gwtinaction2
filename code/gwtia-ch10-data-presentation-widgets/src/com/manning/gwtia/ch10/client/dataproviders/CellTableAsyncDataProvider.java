package com.manning.gwtia.ch10.client.dataproviders;

import java.util.List;
import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.manning.gwtia.ch10.shared.PhotoAlbumService;
import com.manning.gwtia.ch10.shared.PhotoAlbumServiceAsync;
import com.manning.gwtia.ch10.shared.PhotoDetails;
import com.google.gwt.user.cellview.client.AbstractCellTable;
import com.google.gwt.user.cellview.client.ColumnSortList;


/**
 * An AsynchDataProvider using RPC call to get all Photo Information from the server.
 * Used in the GWT in Action 2nd Edition CellList example
 *
 */
public class CellTableAsyncDataProvider extends AsyncDataProvider<PhotoDetails> {

	/**
	 * Reference to the RPC service this provider will use to get data.
	 */
	private PhotoAlbumServiceAsync rpcService;

	/**
	 * Create a new AllDataAsyncDataProvider instance and set up the 
	 * RPC framework that it will use.
	 */
	public CellTableAsyncDataProvider() {
		rpcService = GWT.create(PhotoAlbumService.class);
	}

	/**
	 * {@link #onRangeChanged(HasData)} is called when the table requests a new
	 * range of data. You can push data back to the displays using
	 * {@link #updateRowData(int, List)}.
	 */
	@Override
	protected void onRangeChanged(HasData<PhotoDetails> display) {
		
		// Get the new range required.
		final Range range = display.getVisibleRange();
		
		ColumnSortList sortList = ((AbstractCellTable<PhotoDetails>)display).getColumnSortList();
		String sortOnName = "id";
		boolean isAscending = true;
		if ((sortList!=null)&&(sortList.size()>0)){
			sortOnName = sortList.get(0).getColumn().getDataStoreName();
			isAscending = sortList.get(0).isAscending();
		}
		
		// Call getPhotoList RPC call
		rpcService.getPhotoList(range.getStart(), range.getLength(), sortOnName, isAscending, new AsyncCallback<Vector<PhotoDetails>>() {

			// There's been a failure in the RPC call
			// Normally you would handle that in a good way, 
			// here we just throw up an alert.
			public void onFailure(Throwable caught) {
				Window.alert("Error" + caught.getMessage());
			}

			// We've successfully for the data from the RPC call, 
			// Now we update the row data with that result starting 
			// at a particular row in the cell widget (usually the range start) 
			public void onSuccess(Vector<PhotoDetails> result) {
				updateRowData(range.getStart(), result);
			}
		});
	}
}
