package com.deg540.tdd.Application;

import Appplication.UserLoginService;
import Domain.User;
import com.deg540.tdd.Doubles.DummyFacebookSessionManager;
import com.deg540.tdd.Doubles.FakeFacebookSessionManager;
import com.deg540.tdd.Doubles.SpyFacebookSessionManager;
import com.deg540.tdd.Doubles.StubFacebookSessionManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserLoginServiceTest {
    private UserLoginService userLoginService;

    @Test
    void userIsLoggedIn() throws Exception {
        User user = new User("validUserName");
        userLoginService = new UserLoginService(new DummyFacebookSessionManager());
        List<User> expectedLoggedUsers = new ArrayList<>();
        expectedLoggedUsers.add(user);

        userLoginService.manualLogin(user);

        assertEquals(expectedLoggedUsers, userLoginService.getLoggedUsers());
    }

    @Test
    void userIsNotLoggedInIfAlreadyLogged() throws Exception {
        User user = new User("validUserName");
        userLoginService = new UserLoginService(new DummyFacebookSessionManager());

        userLoginService.manualLogin(user);

        Exception result = Assertions.assertThrowsExactly(Exception.class, () -> userLoginService.manualLogin(user));
        assertEquals("User already logged in", result.getMessage());
    }

    @Test
    void getsLoggedUsers() throws Exception {
        User user = new User("validUserName");
        userLoginService = new UserLoginService(new DummyFacebookSessionManager());
        userLoginService.manualLogin(user);

        List<User> expectedLoggedUsers = new ArrayList<>();
        expectedLoggedUsers.add(user);

        List<User> loggedUsers = userLoginService.getLoggedUsers();

        assertEquals(expectedLoggedUsers, loggedUsers);
    }

    @Test
    void getsTotalUsersLoggedInFacebook() {
        userLoginService = new UserLoginService(new StubFacebookSessionManager());

        int totalExternallyLoggedUsers = userLoginService.getExternalSessions();

        assertEquals(1, totalExternallyLoggedUsers);
    }

    @Test
    void userNotLoggedInInFacebook() {
        userLoginService = new UserLoginService(new FakeFacebookSessionManager());

        String userNotLoggedInMessage = userLoginService.login("invalidUserName", "invalidPassword");

        assertEquals(UserLoginService.MENSAJE_LOGIN_INCORRECTO, userNotLoggedInMessage);
        assertEquals(new ArrayList<>(), userLoginService.getLoggedUsers());
    }

    @Test
    void userLoggedInInFacebook() {
        userLoginService = new UserLoginService(new FakeFacebookSessionManager());
        User user = new User("validUserName");
        List<User> expectedLoggedUsers = new ArrayList<>();
        expectedLoggedUsers.add(user);

        String userNotLoggedInMessage = userLoginService.login("validUserName", "validPassword");

        assertEquals(UserLoginService.MENSAJE_LOGIN_CORRECTO, userNotLoggedInMessage);
        assertEquals(expectedLoggedUsers.get(0).getUserName(), userLoginService.getLoggedUsers().get(0).getUserName());
    }

    @Test
    void notLoggedInUserIsNotLoggedOutFromFacebook() throws Exception {
        SpyFacebookSessionManager spyFacebookSessionManager = new SpyFacebookSessionManager();
        userLoginService = new UserLoginService(spyFacebookSessionManager);
        User user = new User("validUserName");

        String logoutResponse = userLoginService.logout(user);

        assertEquals("User not found", logoutResponse);
        assertEquals(0, spyFacebookSessionManager.logoutCalledTimes());
        assertEquals(new ArrayList<>(), userLoginService.getLoggedUsers());
    }

    @Test
    void loggedInUserIsLoggedOutFromFacebook() throws Exception {
        SpyFacebookSessionManager spyFacebookSessionManager = new SpyFacebookSessionManager();
        userLoginService = new UserLoginService(spyFacebookSessionManager);
        User user = new User("validUserName");
        userLoginService.manualLogin(user);

        String logoutResponse = userLoginService.logout(user);

        assertEquals("OK", logoutResponse);
        assertEquals(1, spyFacebookSessionManager.logoutCalledTimes());
        assertEquals(new ArrayList<>(), userLoginService.getLoggedUsers());
    }
}
