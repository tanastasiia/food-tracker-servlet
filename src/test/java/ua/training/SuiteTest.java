package ua.training;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ua.training.model.service.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({ FoodInfoServiceTest.class, MealServiceTest.class, UserServiceTest.class})
public class SuiteTest {
}
