package ua.training.model.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.training.data.MealTestData;
import ua.training.model.DaoFactory;
import ua.training.model.dao.MealDao;
import ua.training.model.entity.Food;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MealServiceTest {

    @Mock
    private MealDao mealDao;

    @Mock
    private DaoFactory daoFactory;

    @InjectMocks
    private MealService mealService;

    @InjectMocks
    private MealTestData mealTestData;

    @Before
    public void setUpDao() {
        when(daoFactory.createMealDao()).thenReturn(mealDao);
    }

    @Test
    public void sumFoodCaloriesTest() {
        int expected = mealTestData.TODAYS_CALORIES;
        int actual = mealService.sumFoodCalories(mealTestData.TODAYS_MEALS_LIST);

        assertEquals(expected, actual);
    }

    @Test
    public void sumFoodProteinTest() {
        BigDecimal expected = mealTestData.TODAYS_PROTEIN;
        BigDecimal actual = mealService.sumFoodElements(mealTestData.TODAYS_MEALS_LIST, Food::getProtein);

        assertEquals(expected, actual);
    }


}
