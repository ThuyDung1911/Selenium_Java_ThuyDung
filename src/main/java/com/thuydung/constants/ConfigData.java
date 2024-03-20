package com.thuydung.constants;

import com.thuydung.helpers.PropertiesHelper;

public class ConfigData {
    public static boolean HIGHLIGHT_ELEMENT = Boolean.parseBoolean(PropertiesHelper.getValue("HIGHLIGHT_ELEMENT"));
    public static double HIGHLIGHT_TIMEOUT = Double.parseDouble(PropertiesHelper.getValue("HIGHLIGHT_TIMEOUT"));
    public static String AUTHOR = PropertiesHelper.getValue("AUTHOR");
    public static String BROWSER = PropertiesHelper.getValue("BROWSER");

    public static final class HIGHLIGHT {
        public static boolean ELEMENT = true;
        public static double TIMEOUT = 0.0;
    }


}
