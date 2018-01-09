package com.niufennan.jtodos.service.impl;

import com.niufennan.jtodos.models.TodoGroup;
import com.niufennan.jtodos.models.User;
import com.niufennan.jtodos.models.UserMessage;
import com.niufennan.jtodos.persistence.TodoGroupRepository;
import com.niufennan.jtodos.persistence.UserMessageRepository;
import com.niufennan.jtodos.persistence.UserRepository;
import com.niufennan.jtodos.service.UserService;
import com.niufennan.jtodos.utils.Token;
import com.niufennan.jtodos.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService  {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMessageRepository userMessageRepository;

    @Autowired
    private TodoGroupRepository todoGroupRepository;

    @Deprecated
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

    public String login(String username, String password) {
        //判断用户名密码是否符合(此时不加盐加密)
        User user=userRepository.getUserByName(username);
        if(user!=null&&user.getPassword().equals(password)){
            //创建token
            Token token=TokenUtil.generateToken(UUID.randomUUID().toString(),user.getId());
            return token.getSignature();
        }
        return "";
    }

    public String register(String username, String password,String nickname) {
        //已经有了
        User user=userRepository.getUserByName(username);
        if(user!=null){
            return "";
        }
        user=saveUser(username,password);
        System.out.println(user);
        System.out.println(user.getId());
        saveUserMessage(user.getId(),nickname);
        Token token=TokenUtil.generateToken(UUID.randomUUID().toString(),user.getId());
        return token.getSignature();
    }

    private User saveUser(String username, String password){
        User user=new User();
        user.setName(username);
        user.setPassword(password);
        user.setCreateTime(new Date());
        user.setPasswordSalt("a");
        return userRepository.save(user);

    }

    private void saveUserMessage(int userId,String nickName){
        UserMessage userMessage=new UserMessage();
        userMessage.setNickname(nickName);
        userMessage.setUserId(userId);
        userMessageRepository.save(userMessage);
    }

    //创建默认项目组
    //每个新建的用户，都会自动分配一个默认的项目组，即日记
    private void createDefaultTodoGroup(int userId){
        //
        TodoGroup todoGroup=new TodoGroup();
        todoGroup.setCreatetime(new Date());
        todoGroup.setDel(0);
        todoGroup.setStyle(0);
        todoGroup.setIcon("date_range");
        todoGroup.setGroupName("日记");
        todoGroup.setIsDefault(1);//1 默认 0 非默认
        todoGroup.setUserId(userId);
        todoGroupRepository.save(todoGroup).getId();
    }
}
