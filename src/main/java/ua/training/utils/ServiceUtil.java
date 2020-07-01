package ua.training.utils;

import ua.training.model.entity.ActivityLevel;
import ua.training.model.entity.Gender;
import ua.training.model.entity.User;

import java.time.LocalDate;
import java.time.Period;

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

    /**
     *Count calories norm based on user info
     *@param user user for which norm is being counted
     *@return calories norm
     */
    public int countCaloriesNorm(User user) {
        return (int) (ActivityLevel.valueOf(user.getActivityLevel()).getValue()
                * (Gender.valueOf(user.getGender()).getValue()
                + 10 * user.getWeight()
                + 6.25 * user.getHeight()
                - 5 * Period.between(user.getDateOfBirth(), LocalDate.now()).getYears()));
    }




}
