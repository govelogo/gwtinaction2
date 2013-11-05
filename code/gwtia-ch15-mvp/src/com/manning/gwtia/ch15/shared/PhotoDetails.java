package com.manning.gwtia.ch15.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PhotoDetails implements IsSerializable{

	public PhotoDetails(){};
	
	public PhotoDetails(String id){
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	String title;
	String tags;
	String id;
	
	public String getThubnailUrl() {
		return thubnailUrl;
	}
	public void setThubnailUrl(String thubnailUrl) {
		this.thubnailUrl = thubnailUrl;
	}
	public String getLargeUrl() {
		return largeUrl;
	}
	public void setLargeUrl(String largeUrl) {
		this.largeUrl = largeUrl;
	}
	public String getId(){
		return id;
	}

	String thubnailUrl;
	String largeUrl;
	String date;

	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDate() {
		return date;		
	}
}
