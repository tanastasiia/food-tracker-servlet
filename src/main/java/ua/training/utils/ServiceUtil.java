package ua.training.utils;

public class ServiceUtil {
    private static class UtilityHolder {
        private static final ServiceUtil SERVICE_UTIL_INSTANCE = new ServiceUtil();
    }

    private ServiceUtil() {
    }

    public static ServiceUtil getInstance() {
        return UtilityHolder.SERVICE_UTIL_INSTANCE;
    }


    public String capitalizeFirstLetter(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }




}
