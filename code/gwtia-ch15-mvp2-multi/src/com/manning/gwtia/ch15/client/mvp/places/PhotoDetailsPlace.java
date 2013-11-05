package com.manning.gwtia.ch15.client.mvp.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class PhotoDetailsPlace extends Place implements HasBreadCrumb{

	private String photoId;
	
	public String getBreadCrumb(){
		return BreadCrumbs.PHOTO + " " + photoId;
	}

	
	public PhotoDetailsPlace(String photoId){
		this.photoId = photoId;
	}
	
	public String getPhotoId() {
		return photoId;
	}

	@Prefix("showPhotoNumber")
	public static class Tokenizer implements PlaceTokenizer<PhotoDetailsPlace>{

		public PhotoDetailsPlace getPlace(String token) {
			return new PhotoDetailsPlace(token);
		}

		public String getToken(PhotoDetailsPlace place) {
			return place.getPhotoId();
		}
	}
}
