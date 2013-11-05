package com.manning.gwtia.ch12.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class VideoPlaylistItemX extends Composite
{
  private static Binder binder = GWT.create(Binder.class);
  interface Binder extends UiBinder<Widget, VideoPlaylistItemX> {}
  
  @UiField Image image;
  @UiField Anchor title;
  @UiField Label desc;

  public VideoPlaylistItemX (String imageUrl, String titleText, String link, String descText) {
    initWidget(binder.createAndBindUi(this));
    image.setUrl(imageUrl);
    title.setText(titleText);
    title.setHref(link);
    desc.setText(trimTo(descText, 150));
  }

  private String trimTo (String txt, int len) {
    if (txt.length() > len) {
      int lastSpace = txt.lastIndexOf(" ", len);
      txt = txt.substring(0, lastSpace) + "...";
    }
    return txt;
  }
}
