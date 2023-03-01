package Application;

import Domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserLoginService {
    private List<User> loggedUsers = new ArrayList<User>();

    public String manualLogin() {
        return "user logged";
    }
}
