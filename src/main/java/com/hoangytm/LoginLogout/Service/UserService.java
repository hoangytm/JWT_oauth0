package com.hoangytm.LoginLogout.Service;

import com.hoangytm.LoginLogout.Model.User;

public interface UserService {
    public User findByUsername(String email);
    public void saveUser(User user);
}
