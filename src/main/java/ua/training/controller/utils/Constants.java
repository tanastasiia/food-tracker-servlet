package ua.training.controller.utils;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class Constants {

    public static String ERROR = "error";
    private static class BundleHolder {
        private static final ResourceBundle INSTANCE = ResourceBundle.getBundle("constants", Locale.ENGLISH);
    }
    private Constants() {
    }
    public static String getString(String key) {
        return BundleHolder.INSTANCE.getString(key);
    }

    public static Locale LOCALE_UA = new Locale("ua");


}
