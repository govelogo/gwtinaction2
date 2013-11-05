package com.manning.gwtia.ch15.client.mvp;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.manning.gwtia.ch15.client.mvp.places.PhotoDetailsPlace;
import com.manning.gwtia.ch15.client.mvp.places.PhotoListPlace;
import com.manning.gwtia.ch15.client.mvp.places.WelcomePlace;

@WithTokenizers({WelcomePlace.Tokenizer.class, PhotoListPlace.Tokenizer.class, PhotoDetailsPlace.Tokenizer.class})
public interface AppPlacesHistoryMapper extends PlaceHistoryMapper{
}
