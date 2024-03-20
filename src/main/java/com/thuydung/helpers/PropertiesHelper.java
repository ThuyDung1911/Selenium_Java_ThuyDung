package com.thuydung.helpers;

import com.thuydung.utils.LogUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class PropertiesHelper {
    private static Properties properties;
    private static String linkFile;
    private static FileInputStream file;
    private static FileOutputStream out;
    private static final String relPropertiesFilePathDefault = "src/test/resources/configs.properties";

    public static void loadAllFiles() {
        LinkedList<String> files = new LinkedList<>();
        // Add tất cả file Properties vào đây theo mẫu
        files.add("src/test/resources/configs.properties");
        // files.add("src/test/resources/configs.properties");
        // Thứ tự đọc file với những key trùng thằng nào đọc sau cùng thì được hiểu là giá trị đó

        try {
            properties = new Properties();

            for (String f : files) {
                Properties tempProp = new Properties();
                linkFile = SystemHelper.getCurrentDir() + f;
                file = new FileInputStream(linkFile);
                tempProp.load(file);
                properties.putAll(tempProp);
            }
        } catch (IOException ioe) {
            LogUtils.error(ioe.getStackTrace());
        }
    }

    public static void setFile(String relPropertiesFilePath) {
        properties = new Properties();
        try {
            linkFile = SystemHelper.getCurrentDir() + relPropertiesFilePath;
            file = new FileInputStream(linkFile);
            properties.load(file);
            file.close();
        } catch (Exception e) {
            LogUtils.error(e.getStackTrace());
        }
    }

    public static void setDefaultFile() {
        properties = new Properties();
        try {
            linkFile = SystemHelper.getCurrentDir() + relPropertiesFilePathDefault;
            file = new FileInputStream(linkFile);
            properties.load(file);
            file.close();
        } catch (Exception e) {
            LogUtils.error(e.getStackTrace());
        }
    }

    public static String getValue(String key) {
        String VALUE = null;
        try {
            if (file == null) {
                properties = new Properties();
                linkFile = SystemHelper.getCurrentDir() + relPropertiesFilePathDefault;
                file = new FileInputStream(linkFile);
                properties.load(file);
                file.close();
            }
            // Lấy giá trị từ file đã Set
            VALUE = properties.getProperty(key);
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
        }
        return VALUE;
    }

    public static void setValue(String key, String keyValue) {
        try {
            if (file == null) {
                properties = new Properties();
                file = new FileInputStream(SystemHelper.getCurrentDir() + relPropertiesFilePathDefault);
                properties.load(file);
                file.close();
                out = new FileOutputStream(SystemHelper.getCurrentDir() + relPropertiesFilePathDefault);
            } else {
                out = new FileOutputStream(linkFile);
            }
            //Ghi vào cùng file Prop với file lấy ra
            LogUtils.info(linkFile);
            properties.setProperty(key, keyValue);
            properties.store(out, "Write Data to Properties file");
            out.close();
        } catch (Exception e) {
            LogUtils.error(e.getStackTrace());
        }
    }
}
