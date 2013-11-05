package com.manning.gwtia.ch15.client.mvp.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PhotoListTextPlace extends Place{

	private String page;
	
	public PhotoListTextPlace(String token){
		this.page = token;
	}
	
	public String getPage() {
		return page;
	}

	public static class Tokenizer implements PlaceTokenizer<PhotoListTextPlace>{

		public PhotoListTextPlace getPlace(String token) {
			return new PhotoListTextPlace(token);
		}

		public String getToken(PhotoListTextPlace place) {
			return place.getPage();
		}
	}
}
