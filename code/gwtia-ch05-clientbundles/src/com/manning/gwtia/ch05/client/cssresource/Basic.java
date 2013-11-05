package com.manning.gwtia.ch05.client.cssresource;

import com.google.gwt.resources.client.CssResource;

public interface Basic extends CssResource {
    String one();
    
    String two();

    String SOME_BORDER();
    
    String someBorder();

    String themedLabel();
    
    String noCursor();
    
    String background();
}
