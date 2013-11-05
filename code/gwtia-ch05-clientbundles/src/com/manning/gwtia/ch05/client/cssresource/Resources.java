package com.manning.gwtia.ch05.client.cssresource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;

public interface Resources extends ClientBundle {

    Resources IMPL = GWT.create(Resources.class);

    @Source("basic.css")
    Basic basic();

    @Source("Unavalible.cur")
    DataResource unavailable();

    @Source("brushed-metal-002951-light-gray.jpg")
    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource brushedMetal();

    @Source("standard.css")
    Stdcss stdcss();

}
