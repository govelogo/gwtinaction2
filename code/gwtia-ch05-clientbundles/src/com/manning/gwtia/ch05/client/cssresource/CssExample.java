package com.manning.gwtia.ch05.client.cssresource;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.manning.gwtia.ch05.shared.Theme;

public class CssExample extends Composite {
    private static TextArea cssText;
    private ListBox lb = new ListBox();
    HorizontalPanel hp = new HorizontalPanel();
    
    public  CssExample() {

        ResourceServiceAsync.IMPL.getThemes(new AsyncCallback<List<String>>() {

            @Override
            public void onSuccess(List<String> result) {
                lb.addItem("");
                for (String name : result) {
                    lb.addItem(name);
                }
                lb.setSelectedIndex(-1);
                lb.addChangeHandler(new ChangeHandler() {

                    @Override
                    public void onChange(ChangeEvent event) {
                        ListBox lb = (ListBox) event.getSource();
                        int index = lb.getSelectedIndex();
                        if (index > -1) {
                            String theme = lb.getValue(index);
                            ResourceServiceAsync.IMPL.getTheme(theme, new AsyncCallback<HashMap<String, String>>() {

                                @Override
                                public void onFailure(Throwable caught) {
                                    Window.alert("Failed to fetch theme");
                                }

                                @Override
                                public void onSuccess(HashMap<String, String> result) {
                                    Basic css = Resources.IMPL.basic();
                                    Theme.setProperties(result);
                                    if (cssText==null) {
                                        css.ensureInjected();
                                        Label l = new Label("Theme Test.");
                                        l.setStyleName(css.themedLabel());
                                        l.addStyleName(css.noCursor());
                                        hp.add(l);
                                        cssText = new TextArea();
                                        hp.add(cssText);
                                        hp.setStyleName(css.background());
                                    } else {
                                        StyleInjector.inject(css.getText());
                                    }
                                    cssText.setText(css.getText());
                                    
                                }
                            });
                        }
                    }
                });
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Failed to fetch Themes");
            }
        });
        

        hp.add(new Label("Select a theme"));
        hp.add(lb);
        initWidget(hp);

        // Basic css = Resources.IMPL.basic();

        // css.ensureInjected();
        // RootPanel.get().add(new Label(css.someBorder()));
        // RootPanel.get().add(new Label(css.getText()));

    }
}
