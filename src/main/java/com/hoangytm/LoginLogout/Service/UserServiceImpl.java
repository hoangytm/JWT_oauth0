package com.hoangytm.LoginLogour.Service;


import com.hoangytm.LoginLogour.Model.User;
import com.hoangytm.LoginLogour.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

}
