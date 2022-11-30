package com.deg540.tdd.Application;

import Appplication.UserLoginService;
import Domain.User;
import com.deg540.tdd.Doubles.DummyFacebookSessionManager;
import com.deg540.tdd.Doubles.StubFacebookSessionManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserLoginServiceTest {
    private UserLoginService userLoginService;

    @Test
    void userIsLoggedIn() throws Exception {
        User user = new User();
        userLoginService = new UserLoginService(new DummyFacebookSessionManager());
        List<User> expectedLoggedUsers = new ArrayList<>();
        expectedLoggedUsers.add(user);

        userLoginService.manualLogin(user);

        assertEquals(expectedLoggedUsers, userLoginService.getLoggedUsers());
    }

    @Test
    void userIsNotLoggedInIfAlreadyLogged() throws Exception {
        User user = new User();
        userLoginService = new UserLoginService(new DummyFacebookSessionManager());

        userLoginService.manualLogin(user);

        Exception result = Assertions.assertThrowsExactly(Exception.class, () -> userLoginService.manualLogin(user));
        assertEquals("User already logged in", result.getMessage());
    }

    @Test
    void getsLoggedUsers() throws Exception {
        User user = new User();
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
}
