package com.manning.gwtia.ch10.client.cellwidget.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Years implements Comparable<Years> {
	int year;
	ArrayList<Months> months = new ArrayList<Months>();
	
	public int getYear(){
		return this.year;
	}
	
	public List<Months> getMonths(){
		return months;
	}
	
	public int getPhotosNumber(){
		int num = 0;
		Iterator<Months> it1 = months.iterator();
		while (it1.hasNext())
			num += it1.next().getPhotosNumber();
		return num;
	}
	
	public Years(int year){
		this.year = year;
	}

	@Override
	public int compareTo(Years otherYear) {
		return (year-otherYear.getYear());
	}
}
