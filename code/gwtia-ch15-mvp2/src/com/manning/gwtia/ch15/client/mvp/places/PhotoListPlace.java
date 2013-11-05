package com.manning.gwtia.ch15.client.mvp.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PhotoListPlace extends Place{

	private String page;
	
	public PhotoListPlace(String token){
		this.page = token;
	}
	
	public String getPage() {
		return page;
	}

	public static class Tokenizer implements PlaceTokenizer<PhotoListPlace>{

		public PhotoListPlace getPlace(String token) {
			return new PhotoListPlace(token);
		}

		public String getToken(PhotoListPlace place) {
			return place.getPage();
		}
	}
}
