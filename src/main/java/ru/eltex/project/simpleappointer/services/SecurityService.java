package ru.eltex.project.simpleappointer.services;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
