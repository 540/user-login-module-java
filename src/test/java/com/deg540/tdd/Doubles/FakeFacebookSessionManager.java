package com.deg540.tdd.Doubles;

import Infrastructure.FacebookSessionManager;

public class FakeFacebookSessionManager extends FacebookSessionManager {
    @Override
    public boolean login(String userName, String password) {
        return userName.equals("validUserName") && password.equals("validPassword");
    }

    @Override
    public int getSessions() {
        throw new RuntimeException();
    }
}

