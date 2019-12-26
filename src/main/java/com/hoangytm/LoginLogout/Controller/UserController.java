package com.hoangytm.LoginLogour.Controller;


import com.hoangytm.LoginLogour.Model.User;
import com.hoangytm.LoginLogour.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Provider;

@RestController
public class UserController {


    @Autowired
    UserService userService;

    @GetMapping("/email")
    public String getUser() {
        return "hello";
    }

    @GetMapping("/login")
    public String login(){
        return "success";
    }


    @PostMapping("/registration")
    public String registration(@RequestBody User user) {

        User userExists = userService.findByUsername(user.getUsername());
        if (userExists != null) {
            return " existed user";

        } else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
             userService.saveUser(user);
             return "saved success";

        }
    }


@GetMapping("/auth")
public String auth() {
    return "auth success";
}
}





