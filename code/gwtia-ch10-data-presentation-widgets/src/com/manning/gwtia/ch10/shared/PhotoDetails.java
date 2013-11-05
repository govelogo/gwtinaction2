package com.manning.gwtia.ch10.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PhotoDetails implements IsSerializable, Comparable<PhotoDetails> {

	Date date;

	String id;

	String largeUrl;
	String tags;
	String[] tagsList;

	String thubnailUrl;

	String title;

	public PhotoDetails() {
	}

	public PhotoDetails(String id) {
		this.id = id;
	}

	@Override
	public int compareTo(PhotoDetails o) {
		return this.id.compareTo(o.id);
	}

	public Integer getComparableId() {
		return Integer.parseInt(id);
	}

	public Date getDate() {
		return date;
	}
	
	// Need to use Date as this is also used on client side
	@SuppressWarnings("deprecation")
	public int getYear() {
		return 	date.getYear()+1900;
	}
	
	@SuppressWarnings("deprecation")
	public int getMonth() {
		return date.getMonth()+1;
	}
	
	@SuppressWarnings("deprecation")
	public int getDay() {
		return date.getDate();
		
	}

	public String getId() {
		return id;
	}

	public String getLargeUrl() {
		return largeUrl;
	}

	public String getTags() {
		String toReturn = "";
		toReturn += tagsList[0];
		for (int loop=1; loop <tagsList.length; loop++)
			toReturn += ", " +tagsList[loop];
		return toReturn;
	}

	public String[] getTagsList() {
		return tagsList;
	}

	public String getThubnailUrl() {
		return thubnailUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setLargeUrl(String largeUrl) {
		this.largeUrl = largeUrl;
	}

	public void setTags(String tags) {
		String[] theTags = tags.split(",");
		this.tagsList = theTags;
	}
	
	public void setTags(String[] tags){
		this.tagsList = tags;
	}

	public void setThubnailUrl(String thubnailUrl) {
		this.thubnailUrl = thubnailUrl;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
