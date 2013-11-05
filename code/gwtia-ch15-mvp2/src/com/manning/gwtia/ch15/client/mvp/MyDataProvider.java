package com.manning.gwtia.ch15.client.mvp;

import java.util.List;
import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.manning.gwtia.ch15.client.mvp.events.AppBusyEvent;
import com.manning.gwtia.ch15.client.mvp.events.AppFreeEvent;
import com.manning.gwtia.ch15.shared.PhotoAlbumServiceAsync;
import com.manning.gwtia.ch15.shared.PhotoDetails;

public class MyDataProvider extends AsyncDataProvider<PhotoDetails> {

	private PhotoAlbumServiceAsync rpcService;
	private EventBus appEventBus;

	public MyDataProvider(ClientFactory clientFactory) {
		this.rpcService = clientFactory.getPhotoServices();
		this.appEventBus = clientFactory.getEventBus();
	}

	/**
	 * {@link #onRangeChanged(HasData)} is called when the table requests a new
	 * range of data. You can push data back to the displays using
	 * {@link #updateRowData(int, List)}.
	 */
	@Override
	protected void onRangeChanged(HasData<PhotoDetails> display) {
		// Get the new range.
		final Range range = display.getVisibleRange();
		GWT.log(range.getStart() +"->"+(range.getStart()+range.getLength()), null);

		appEventBus.fireEvent(new AppBusyEvent());
		rpcService.getPhotoList(range.getStart(), range.getLength(), new AsyncCallback<Vector<PhotoDetails>>() {
			public void onSuccess(Vector<PhotoDetails> result) {
				updateRowData(range.getStart(), result);
				appEventBus.fireEvent(new AppFreeEvent());
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error" + caught.getMessage());
				appEventBus.fireEvent(new AppFreeEvent());
			}
		});
	}
}
