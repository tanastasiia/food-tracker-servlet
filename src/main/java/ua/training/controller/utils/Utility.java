package ua.training.controller.utils;

import java.util.List;
import java.util.Locale;

public class Utility {
    private static class UtilityHolder {
        private static final Utility UTILITY_INSTANCE = new Utility();
    }

    private Utility() {
    }

    public static Utility getInstance() {
        return UtilityHolder.UTILITY_INSTANCE;
    }


    public Locale LOCALE_UA = new Locale("ua");




}
