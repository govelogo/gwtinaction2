package com.manning.gwtia.ch16.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PhotoDetails implements IsSerializable {

	String title;;
	String tags;
	String id;
	String thubnailUrl;
	String largeUrl;
	String date;

	public PhotoDetails() {}
	
	public PhotoDetails(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public String getId() {
		return id;
	}
	public String getLargeUrl() {
		return largeUrl;
	}
	public String getTags() {
		return tags;
	}

	public String getThubnailUrl() {
		return thubnailUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setLargeUrl(String largeUrl) {
		this.largeUrl = largeUrl;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public void setThubnailUrl(String thubnailUrl) {
		this.thubnailUrl = thubnailUrl;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
