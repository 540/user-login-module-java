package com.deg540.tdd.Doubles;

import Infrastructure.FacebookSessionManager;

public class DummyFacebookSessionManager extends FacebookSessionManager {
    @Override
    public int getSessions() {
        throw new RuntimeException();
    }
}
