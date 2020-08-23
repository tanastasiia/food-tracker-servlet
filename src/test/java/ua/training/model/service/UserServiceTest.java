package ua.training.model.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.training.data.UserTestData;
import ua.training.model.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;

import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @Mock
    private DaoFactory daoFactory;

    @InjectMocks
    private UserService userService;

    @InjectMocks
    private UserTestData userTestData;


    @Before
    public void setUpDao() {
        when(daoFactory.createUserDao()).thenReturn(userDao);
    }

    @Test
    public void findAllUsersTest() throws Exception {
        when(userDao.findAll(anyInt(), anyInt())).thenReturn(userTestData.USERS_LIST);

        List<User> expected = userTestData.USERS_LIST.subList(0, 5);
        List<User> actual = userService.findAllUsers(5,0);

        verify(userDao).findAll(5,0);
        assertThat(actual, is(expected));

    }

    @Test
    public void countAllUsersTest() throws ServerException {
        when(userDao.countAll()).thenReturn(userTestData.USERS_LIST.size());

        int expected = userTestData.USERS_LIST.size();
        int actual = userService.countAllUsers();

        verify(userDao).countAll();
        assertThat(actual, is(expected));
    }

    @Test
    public void authenticationCorrectTest() throws ServerException {
        when(userDao.findByUsername(anyString())).thenReturn(Optional.of(userTestData.USER));

        User expected = userTestData.USER;
        Optional<User> actual = userService.authentication(userTestData.USER.getUsername(), "qwerty");

        verify(userDao).findByUsername(userTestData.USER.getUsername());
        assertTrue(actual.isPresent());
        assertThat(actual.get(), is(expected));
    }

    @Test
    public void authenticationWrongTest() throws ServerException {
        User user = userTestData.USER;

        when(userDao.findByUsername(anyString())).thenReturn(Optional.of(user));

        Optional<User> actual = userService.authentication(userTestData.USER.getUsername(), "str");

        verify(userDao).findByUsername(userTestData.USER.getUsername());
        assertFalse(actual.isPresent());
    }

}
