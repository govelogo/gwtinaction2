package com.manning.gwtia.ch10.client.cellwidget.common;

import java.util.ArrayList;
import java.util.List;

import com.manning.gwtia.ch10.shared.PhotoDetails;

public class Days implements Comparable<Days>{
	int day;
	ArrayList<PhotoDetails> photos = new ArrayList<PhotoDetails>();
	
	public int getDay(){
		return day;
	}
	
	public List<PhotoDetails> getPhotos(){
		return photos;
	}
	
	public int getPhotosNumber(){
		return photos.size();
	}
	
	
	public Days(int day){
		this.day = day;
	}
	
	@Override
	public int compareTo(Days otherDay) {
		return (day-otherDay.getDay());
	}
}
