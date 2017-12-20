package com.niufennan.jtodos.service.impl;

import com.niufennan.jtodos.models.User;
import com.niufennan.jtodos.persistence.UserRepository;
import com.niufennan.jtodos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService  {
    @Autowired
    private UserRepository userRepository;
    public User getUserByName(String username) {
        User user=null;
        //获取用户
        user=userRepository.getUserByName(username);
        if(null==user){
            //新用户
            user=new User();
            user.setName(username);
            user.setId(userRepository.save(user).getId());
        }
        return user;
    }

    public User get(int id) {
        return userRepository.getOne(id);
    }
}
