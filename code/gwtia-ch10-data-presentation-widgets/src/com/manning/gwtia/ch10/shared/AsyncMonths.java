package com.manning.gwtia.ch10.shared;


import com.google.gwt.user.client.rpc.IsSerializable;

public class AsyncMonths implements Comparable<AsyncMonths>, IsSerializable{
	int month;
	
	public int getMonth(){
		return month;
	}
	
	
	public int getPhotosNumber(){
		int num = 0;
//		Iterator<Days> it1 = days.iterator();
//		while (it1.hasNext())
//			num += it1.next().getPhotosNumber();
		return num;
	}
	
	
	public AsyncMonths(){}
	
	public AsyncMonths(int month){
		this.month = month;
	}
	
	@Override
	public int compareTo(AsyncMonths otherMonth) {
		return (month-otherMonth.getMonth());
	}
	
}

