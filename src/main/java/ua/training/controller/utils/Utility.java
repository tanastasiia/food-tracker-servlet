package ua.training.controller.utils;

import java.util.List;

public class Utility {
    private static class UtilityHolder {
        private static final Utility UTILITY_INSTANCE = new Utility();
    }

    private Utility() {
    }

    public static Utility getInstance() {
        return UtilityHolder.UTILITY_INSTANCE;
    }

    public String formRedirect(String url, List<String> params){
        StringBuilder pathBuilder = new StringBuilder(url);
        pathBuilder.append("?");
        //String.join("&")
        //TODO
        return "";
    }
}
