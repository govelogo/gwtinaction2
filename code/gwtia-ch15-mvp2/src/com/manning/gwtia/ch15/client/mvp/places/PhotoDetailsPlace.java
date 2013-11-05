package com.manning.gwtia.ch15.client.mvp.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class PhotoDetailsPlace extends Place{

	private String photoId;
	
	public PhotoDetailsPlace(String token){
		this.photoId = token;
	}
	
	public String getPhotoId() {
		return photoId;
	}

	@Prefix("showPhotoNumber")
	public static class Tokenizer implements PlaceTokenizer<PhotoDetailsPlace>{

		public PhotoDetailsPlace getPlace(String token) {
			// TODO Auto-generated method stub
			return new PhotoDetailsPlace(token);
		}

		public String getToken(PhotoDetailsPlace place) {
			return place.getPhotoId();
		}
	}
}
