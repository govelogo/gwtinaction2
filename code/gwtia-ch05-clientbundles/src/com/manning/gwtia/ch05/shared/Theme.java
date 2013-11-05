package com.manning.gwtia.ch05.shared;

import java.util.HashMap;

public class Theme {
    public static final String BOLD_FONTS = "boldFonts";
    private static HashMap<String, String> props;
    public static void setProperties(HashMap<String, String> props) {
        Theme.props = props;
    }
    public static String getProperty(String prop, String def) {
        String value = def;
        if (null != props && null != prop) {
            if (props.containsKey(prop)) {
                value = props.get(prop);
            }
        }
        return value;
    }
    public static boolean isUseBoldFonts() {
        boolean boldFonts = false;
        if(props.containsKey(BOLD_FONTS)) {
            boldFonts = Boolean.parseBoolean(props.get(BOLD_FONTS));
        }
        return boldFonts;
    }
}
