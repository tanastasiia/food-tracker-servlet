package ua.training.model;

import ua.training.model.entity.ActivityLevel;
import ua.training.model.entity.Food;
import ua.training.model.entity.Gender;
import ua.training.model.entity.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;
import java.util.Optional;

public class ServiceUtil {

    private final Integer MILLIGRAMS_TO_GRAMS = 1000;
    public final Locale LOCALE_UA = new Locale("ua");

    private static class UtilityHolder {
        private static final ServiceUtil SERVICE_UTIL_INSTANCE = new ServiceUtil();
    }

    private ServiceUtil() {
    }

    public static ServiceUtil getInstance() {
        return UtilityHolder.SERVICE_UTIL_INSTANCE;
    }

    public String capitalizeFirstLetter(String str){
        return str==null || str.isEmpty() ? null : str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
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

    /**
     * Converts grams to milligrams
     * @param grams grams to convert
     * @return milligrams
     */
    public Integer toMilligrams(BigDecimal grams){
        return  grams.multiply(BigDecimal.valueOf(MILLIGRAMS_TO_GRAMS)).intValue();
    }

    /**
     * Returns localized food name
     *
     * @param food Food which name is returned
     * @return name in needed locale (ukrainian or english) if  name in that locale exists in database and in another language if not.
     * Returns empty string if both names are absent.
     */
    public String getLocalizedFoodName(Food food, Locale locale) {
        Optional<String> nameUa = Optional.ofNullable(food.getNameUa());
        Optional<String> name = Optional.ofNullable(food.getName());
        return locale.equals(LOCALE_UA)
                ? nameUa.orElse(name.orElse(""))
                : name.orElse(nameUa.orElse(""));
    }





}
