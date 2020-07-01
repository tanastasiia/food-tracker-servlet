package ua.training.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.training.data.FoodInfoTestData;
import ua.training.data.FoodTestData;
import ua.training.model.DaoFactory;
import ua.training.model.dao.FoodInfoDao;
import ua.training.model.dto.FoodDto;
import ua.training.model.entity.FoodInfo;

import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FoodInfoServiceTest {

    @Mock
    private FoodInfoDao foodInfoDao;

    @Mock
    private DaoFactory daoFactory;

    @InjectMocks
    private FoodInfoService foodInfoService;

    @InjectMocks
    private FoodInfoTestData foodInfoTestData;

    @InjectMocks
    private FoodTestData foodTestData;

    @Before
    public void setUpDao() {
        when(daoFactory.createFoodInfoDao()).thenReturn(foodInfoDao);
    }

    @Test
    public void findFoodByFoodNameAndUserLowerCaseTest() throws ServerException {
        when(foodInfoDao.findAllByFoodNameOrFoodNameUaAndUserIdOrGlobal(anyString(), anyLong()))
                .thenReturn(Optional.of(foodInfoTestData.FOOD_INFO_GLOBAL));

        FoodInfo expected = foodInfoTestData.FOOD_INFO_GLOBAL;

        String foodName = expected.getFood().getName().toLowerCase();
        Optional<FoodInfo> actual = foodInfoService.findFoodByFoodNameAndUser(foodName, expected.getUser().getId());

        verify(foodInfoDao).findAllByFoodNameOrFoodNameUaAndUserIdOrGlobal(anyString(), anyLong());
        assertTrue(actual.isPresent());
        assertThat(actual.get(), is(expected));
    }

    @Test
    public void findFoodByFoodNameAndUserUpperCaseTest() throws ServerException {
        when(foodInfoDao.findAllByFoodNameOrFoodNameUaAndUserIdOrGlobal(anyString(), anyLong()))
                .thenReturn(Optional.of(foodInfoTestData.FOOD_INFO_GLOBAL));

        FoodInfo expected = foodInfoTestData.FOOD_INFO_GLOBAL;

        String foodName = expected.getFood().getName().toUpperCase();
        Optional<FoodInfo> actual = foodInfoService.findFoodByFoodNameAndUser(foodName, expected.getUser().getId());

        verify(foodInfoDao).findAllByFoodNameOrFoodNameUaAndUserIdOrGlobal(anyString(), anyLong());
        assertTrue(actual.isPresent());
        assertThat(actual.get(), is(expected));
    }

    @Test
    public void saveFoodTest() throws ServerException, SQLException {

        FoodInfo expected = foodInfoTestData.FOOD_INFO_NOT_GLOBAL;

        when(foodInfoDao.findAllByFoodNameOrFoodNameUaAndUserIdOrGlobal(anyString(), anyLong()))
                .thenReturn(Optional.empty());
        doReturn(expected).when(foodInfoDao).saveFood(any());

        Optional<FoodInfo> actual = foodInfoService.saveFood(foodTestData.FOOD_DTO, expected.getUser(), false);

        verify(foodInfoDao).findAllByFoodNameOrFoodNameUaAndUserIdOrGlobal(anyString(), anyLong());
        verify(foodInfoDao).saveFood(any(FoodInfo.class));
        assertTrue(actual.isPresent());
        assertThat(actual.get(), is(expected));
    }

    @Test
    public void savePresentFoodTest() throws ServerException, SQLException {

        FoodInfo expected = foodInfoTestData.FOOD_INFO_NOT_GLOBAL;

        when(foodInfoDao.findAllByFoodNameOrFoodNameUaAndUserIdOrGlobal(anyString(), anyLong())).thenReturn(Optional.of(expected));
        doReturn(expected).when(foodInfoDao).saveFood(any());

        Optional<FoodInfo> actual = foodInfoService.saveFood(foodTestData.FOOD_DTO, expected.getUser(), false);

        verify(foodInfoDao).findAllByFoodNameOrFoodNameUaAndUserIdOrGlobal(anyString(), anyLong());
        verify(foodInfoDao, never()).saveFood(any(FoodInfo.class));
        assertFalse(actual.isPresent());
    }

    @Test
    public void findAllFoodTest() throws ServerException {
        when(foodInfoDao.findAll(anyInt(), anyInt())).thenReturn(foodInfoTestData.FOOD_INFO_LIST);

        List<FoodInfo> expected = foodInfoTestData.FOOD_INFO_LIST.subList(0, 5);
        List<FoodInfo> actual = foodInfoService.findAllFood(5, 0);

        verify(foodInfoDao).findAll(5, 0);
        assertThat(actual, is(expected));
    }

}
