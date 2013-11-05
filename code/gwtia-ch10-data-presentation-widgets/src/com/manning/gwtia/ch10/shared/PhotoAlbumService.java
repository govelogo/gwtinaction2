package com.manning.gwtia.ch10.shared;

import java.util.List;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The service interface for the PhotoApp server-side service.
 */
@RemoteServiceRelativePath("service")
public interface PhotoAlbumService extends RemoteService
{
	/**
	 * Return a list of days that have photos in a particular month and year
	 * @param year year to check for
	 * @param month month to check for
	 * @param range
	 * @return
	 */
	List<AsyncDays> getDaysList(int year, int month, int rangeStart, int rangeLength);
	
	/**
	 * 
	 * @param year
	 * @param rangeStart
	 * @param rangeLength
	 * @return
	 */
	List<AsyncMonths> getMonthsList(int year, int rangeStart, int rangeLength);
	
	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param rangeStart
	 * @param rangeLength
	 * @return
	 */
	List<PhotoDetails> getPhotoByDateList(int year, int month, int day, int rangeStart, int rangeLength);
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws PhotoNotFoundException
	 */
	PhotoDetails getPhotoDetails(String id) throws PhotoNotFoundException;
	
	/**
	 * 
	 * @param rangeStart
	 * @param rangeLength
	 * @return
	 */
	Vector<PhotoDetails> getPhotoList(int rangeStart, int rangeLength);
	
	/**
	 * 
	 * @param rangeStart
	 * @param rangeLength
	 * @param sortOn
	 * @param isAscending
	 * @return
	 */
	Vector<PhotoDetails> getPhotoList(int rangeStart, int rangeLength, String sortOn, boolean isAscending);
	
	/**
	 * 
	 * @param rangeStart
	 * @param rangeLength
	 * @return
	 */
	List<AsyncYears> getYearsList(int rangeStart, int rangeLength);

	/**
	 * 
	 * @param photoDetails
	 * @return
	 */
	PhotoDetails updatePhotoDetails(PhotoDetails photoDetails);
}