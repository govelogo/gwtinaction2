package com.manning.gwtia.ch10.client.cellwidget.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Months implements Comparable<Months>{
	int month;
	ArrayList<Days> days = new ArrayList<Days>();
	
	public int getMonth(){
		return month;
	}
	
	public List<Days> getDays(){
		return days;
	}
	
	public int getPhotosNumber(){
		int num = 0;
		Iterator<Days> it1 = days.iterator();
		while (it1.hasNext())
			num += it1.next().getPhotosNumber();
		return num;
	}
	
	
	public Months(int month){
		this.month = month;
	}
	
	@Override
	public int compareTo(Months otherMonth) {
		return (month-otherMonth.getMonth());
	}
	
}

