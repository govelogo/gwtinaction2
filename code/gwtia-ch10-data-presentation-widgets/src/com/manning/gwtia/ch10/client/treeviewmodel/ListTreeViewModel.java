package com.manning.gwtia.ch10.client.treeviewmodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;
import com.google.gwt.view.client.TreeViewModel.NodeInfo;
import com.manning.gwtia.ch10.client.celltypes.custom.PhotoCellWithUiBinder;
import com.manning.gwtia.ch10.client.cellwidget.common.Days;
import com.manning.gwtia.ch10.client.cellwidget.common.Months;
import com.manning.gwtia.ch10.client.cellwidget.common.PsuedoDataSource;
import com.manning.gwtia.ch10.client.cellwidget.common.Years;
import com.manning.gwtia.ch10.shared.PhotoDetails;

public class ListTreeViewModel implements TreeViewModel {
	
	
	private void createModel(List<PhotoDetails> photos) {
		Iterator<PhotoDetails> it = photos.iterator();
		while (it.hasNext()) {
			PhotoDetails photo = it.next();
			int year = photo.getYear();
			int month = photo.getMonth();
			int day = photo.getDay();
			ListIterator<Years> it2 = years.listIterator();
			Years newYear = null;
			Months newMonth = null;
			Days newDay = null;
			while(it2.hasNext()){
				Years yearToCheck = it2.next();
				if (yearToCheck.getYear()==year){
					newYear=yearToCheck;
				}
			}
			if (newYear==null){
				newYear = new Years(year);
				newMonth = new Months(month);
				newDay = new Days(day);
				years.add(newYear);
				newYear.getMonths().add(newMonth);
				newMonth.getDays().add(newDay);
				newDay.getPhotos().add(photo);
			} else {
				ListIterator<Months> it3 = newYear.getMonths().listIterator();
				while (it3.hasNext()){
					Months monthToCheck = it3.next();
					if (monthToCheck.getMonth()==month){
						newMonth=monthToCheck;
					}
				}
				if (newMonth==null){
					newMonth = new Months(month);
					newDay = new Days(day);
					newYear.getMonths().add(newMonth);
					newMonth.getDays().add(newDay);
					newDay.getPhotos().add(photo);
				} else {
					ListIterator<Days> it4 = newMonth.getDays().listIterator();
					while (it4.hasNext()){
						Days dayToCheck = it4.next();
						if (dayToCheck.getDay()==day){
							newDay=dayToCheck;
						}
					}
					if (newDay==null){
						newDay = new Days(day);
						newMonth.getDays().add(newDay);
						newDay.getPhotos().add(photo);
					} else {
						// Just add the photo
						newDay.getPhotos().add(photo);
					}
				} 
			}
		}
	}
	
	
	public void populate(){
	
	final int maxData = 2000;
	final int chunks = 400;
	
	Scheduler.get().scheduleIncremental(
			new RepeatingCommand(){
		int counter = 0;
	
		@Override
		public boolean execute() {
			createModel(PsuedoDataSource.createData(chunks));
			Collections.sort(getYearDataProvider().getList());
			getYearDataProvider().refresh();
			if(getMonthDataProvider()!=null){
				getMonthDataProvider().refresh();
			}
			if(getDayDataProvider()!=null){
				getDayDataProvider().refresh();
			}
			if(getPhotoDataProvider()!=null){
				getPhotoDataProvider().refresh();
			}
			counter += chunks;
			return (counter<maxData);
		}
	});
}
	
	private List<Years> years;
	
	public ListDataProvider<Years> getYearDataProvider() {
		return yearDataProvider;
	}

	public ListDataProvider<Months> getMonthDataProvider() {
		return monthDataProvider;
	}

	public ListDataProvider<Days> getDayDataProvider() {
		return dayDataProvider;
	}

	public ListDataProvider<PhotoDetails> getPhotoDataProvider() {
		return photoDataProvider;
	}

	ListDataProvider<Years> yearDataProvider;
	ListDataProvider<Months> monthDataProvider;
	ListDataProvider<Days> dayDataProvider;
	ListDataProvider<PhotoDetails> photoDataProvider;
		
	public ListTreeViewModel(){
		years = new ArrayList<Years>();
		populate();
	}

	/**
	 * Get the {@link NodeInfo} that provides the children of the specified
	 * value.
	 */
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if (value==null){
			// LEVEL 0 - so return the years
	    	yearDataProvider = new ListDataProvider<Years>(years);
	    	Cell<Years> cell = new AbstractCell<Years>() {
				@Override
				public void render(
						com.google.gwt.cell.client.Cell.Context context,
						Years value, SafeHtmlBuilder sb) {
					if (value != null) {
						sb.appendEscaped(value.getYear()+ " ("+value.getPhotosNumber()+")");						
					}
				}
	    	};
	    	return new DefaultNodeInfo<Years>(yearDataProvider, cell);
	    } else if (value instanceof Years){
	    	// LEVEL 1 - so return the months
	    	monthDataProvider = new ListDataProvider<Months>(((Years)value).getMonths());
	    	Collections.sort(monthDataProvider.getList());
	    	Cell<Months> cell = new AbstractCell<Months>() {
				@Override
				public void render(
						com.google.gwt.cell.client.Cell.Context context,
						Months value, SafeHtmlBuilder sb) {
					if (value != null) {
						sb.appendEscaped(LocaleInfo.getCurrentLocale().getDateTimeFormatInfo().monthsFull()[value.getMonth()-1] + " ("+value.getPhotosNumber()+")");
					}
				}
	    	};
	    	return new DefaultNodeInfo<Months>(monthDataProvider, cell);
	    } else if (value instanceof Months){
	    	// LEVEL 2 - so return the days
	    	dayDataProvider = new ListDataProvider<Days>(((Months)value).getDays());
	    	Collections.sort(dayDataProvider.getList());
	    	Cell<Days> cell = new AbstractCell<Days>() {
				@Override
				public void render(
						com.google.gwt.cell.client.Cell.Context context,
						Days value, SafeHtmlBuilder sb) {
					if (value != null) {
						sb.appendEscaped(value.getDay() + "th ("+value.getPhotosNumber()+")");						
					}
				}
	    	};
	    	return new DefaultNodeInfo<Days>(dayDataProvider, cell);
	    } else if (value instanceof Days){
	    	// LEVEL 3 - so return the photo
	    	photoDataProvider = new ListDataProvider<PhotoDetails>(((Days)value).getPhotos());
	    	return new DefaultNodeInfo<PhotoDetails>(photoDataProvider, new PhotoCellWithUiBinder());
	    }
	    return null;
	}

	/**
	 * Check if the specified value represents a leaf node. Leaf nodes
	 * cannot be opened.
	 */
	public boolean isLeaf(Object value) {
		return (value instanceof PhotoDetails);
	}
}
