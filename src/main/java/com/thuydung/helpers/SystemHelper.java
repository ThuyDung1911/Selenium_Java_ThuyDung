package com.thuydung.helpers;

import java.io.File;
import java.util.ArrayList;

public class SystemHelper {
    public static String getCurrentDir() {
        String current = System.getProperty("user.dir") + File.separator;
        return current;
    }

    public static ArrayList<String> splitString(String str, String valueSplit) {
        ArrayList<String> arrayListString = new ArrayList<>();
        for (String s : str.split(valueSplit, 0)) {
            arrayListString.add(s);
        }
        return arrayListString;
    }
}
