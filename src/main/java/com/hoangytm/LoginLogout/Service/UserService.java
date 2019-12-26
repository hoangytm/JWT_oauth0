package com.hoangytm.LoginLogour.Service;

import com.hoangytm.LoginLogour.Model.User;

public interface UserService {
    public User findByUsername(String email);
    public void saveUser(User user);
}
