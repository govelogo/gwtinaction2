package com.manning.gwtia.ch10.shared;


import com.google.gwt.user.client.rpc.IsSerializable;


public class AsyncYears implements Comparable<AsyncYears>, IsSerializable {

	int year;
	
	public int getYear(){
		return this.year;
	}
	
	
	public int getPhotosNumber(){
		int num = 0;
		return num;
	}
	
	public AsyncYears(){}
	
	public AsyncYears(int year){
		this.year = year;
	}

	public int compareTo(AsyncYears otherYear) {
		return (year-otherYear.getYear());
	}
}
