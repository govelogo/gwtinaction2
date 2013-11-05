package com.manning.gwtia.ch10.shared;

import java.util.List;
import java.util.Vector;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PhotoAlbumServiceAsync {

	Request updatePhotoDetails(PhotoDetails photoDetails, AsyncCallback<PhotoDetails> asyncCallback);
	Request getPhotoDetails(String id, AsyncCallback<PhotoDetails> callback);
	Request getPhotoList(int rangeStart, int rangeLength, AsyncCallback<Vector<PhotoDetails>> callback);
	Request getPhotoList(int rangeStart, int rangeLength, String sortOn, boolean isAscending, AsyncCallback<Vector<PhotoDetails>> callback);
	Request getYearsList(int rangeStart, int rangeLength,AsyncCallback<List<AsyncYears>> callback);
	Request getMonthsList(int year, int rangeStart, int rangeLength, AsyncCallback<List<AsyncMonths>> callback);
	Request getDaysList(int year, int month, int rangeStart, int rangeLength, AsyncCallback<List<AsyncDays>> callback);
	Request getPhotoByDateList(int year, int month, int day, int rangeStart, int rangeLength, AsyncCallback<List<PhotoDetails>> callback);
}
