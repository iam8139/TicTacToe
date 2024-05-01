package org.game.service;

public interface MatrixGame {
    int getCount();

    void setUser(String user1, String user2);
    void mark(int index);
    boolean won(int index);

    void checkStatus();
}
