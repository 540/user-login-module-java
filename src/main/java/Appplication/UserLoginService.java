package Appplication;

import Domain.User;
import Infrastructure.FacebookSessionManager;

import java.util.ArrayList;
import java.util.List;

public class UserLoginService {
    private List<User> loggedUsers = new ArrayList<User>();
    private FacebookSessionManager facebookSessionManager;

    public UserLoginService(FacebookSessionManager facebookSessionManager) {
        this.facebookSessionManager = facebookSessionManager;
    }

    public void manualLogin(User user) throws Exception {
        if (loggedUsers.contains(user)){
            throw new Exception("User already logged in");
        }

        loggedUsers.add(user);
    }

    public List<User> getLoggedUsers() {
        return loggedUsers;
    }

    public int getExternalSessions() {
        return facebookSessionManager.getSessions();
    }
}
