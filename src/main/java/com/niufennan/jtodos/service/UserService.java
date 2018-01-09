package com.niufennan.jtodos.service;

import com.niufennan.jtodos.models.User;

public interface UserService {
    @Deprecated
    User getUserByName(String username);
    User get(int id);
    String login(String username,String password);

    String register(String username,String password,String nickname);
}
