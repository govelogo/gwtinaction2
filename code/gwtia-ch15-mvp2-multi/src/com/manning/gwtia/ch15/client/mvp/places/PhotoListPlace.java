package com.manning.gwtia.ch15.client.mvp.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PhotoListPlace extends Place implements HasBreadCrumb{

	private int page;
	
	public String getBreadCrumb(){
		return BreadCrumbs.LIST + " " + page;
	}
	
	public PhotoListPlace(String token){
		this.page = Integer.parseInt(token);
	}
	
	public int getPage() {
		return page;
	}

	public static class Tokenizer implements PlaceTokenizer<PhotoListPlace>{

		public PhotoListPlace getPlace(String token) {
			return new PhotoListPlace(token);
		}

		public String getToken(PhotoListPlace place) {
			return ""+place.getPage();
		}
	}
}
