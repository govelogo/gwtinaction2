package com.manning.gwtia.ch05.client.cssresource;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ResourceServiceAsync {
    static final ResourceServiceAsync IMPL = (ResourceServiceAsync) GWT.create(ResourceService.class);

    void getThemes(AsyncCallback<List<String>> callback);

    void getTheme(String name, AsyncCallback<HashMap<String, String>> callback);
}
