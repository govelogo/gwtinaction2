package com.manning.gwtia.ch13.client.i18nstatic.clientbundle;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * Flag images are from: http://www.ip2location.com/flagsoftheworld.aspx
 *
 */
public interface LocaleFlags extends ClientBundle{

	@Source("flag.gif")
	public ImageResource getLocaleFlag();
}
