package com.niufennan.jtodos.service;

import com.niufennan.jtodos.models.User;

public interface UserService {
    User getUserByName(String username);
    User get(int id);
}
