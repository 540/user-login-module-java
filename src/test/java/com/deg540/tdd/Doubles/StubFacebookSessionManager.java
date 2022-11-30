package com.deg540.tdd.Doubles;

import Infrastructure.FacebookSessionManager;

public class StubFacebookSessionManager extends FacebookSessionManager {
    @Override
    public boolean login(String userName, String password) {
        throw new RuntimeException();
    }

    @Override
    public int getSessions() {
        return 1;
    }
}

