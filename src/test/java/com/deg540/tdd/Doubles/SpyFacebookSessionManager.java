package com.deg540.tdd.Doubles;

import Domain.User;
import Infrastructure.FacebookSessionManager;

public class SpyFacebookSessionManager extends FacebookSessionManager {
    private int times = 0;

    @Override
    public boolean login(String userName, String password) {
        throw new RuntimeException();
    }

    @Override
    public int getSessions() {
        throw new RuntimeException();
    }

    @Override
    public void logout(User user) {
        times++;
    }

    public int logoutCalledTimes() {
        return times;
    }
}

