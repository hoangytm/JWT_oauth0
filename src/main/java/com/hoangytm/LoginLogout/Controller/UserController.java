package com.hoangytm.LoginLogout.Controller;


import com.hoangytm.LoginLogout.Model.BlackToken;
import com.hoangytm.LoginLogout.Service.BlackTokenService;
import com.hoangytm.LoginLogout.Service.UserService;
import com.hoangytm.LoginLogout.Model.User;
import com.hoangytm.LoginLogout.Util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
public class UserController {


    @Autowired
    UserService userService;
    @Autowired
    BlackTokenService blackTokenService;

    @GetMapping("/email")
    public String getUser() {
//        Boolean isBlackToken= blackTokenService.checkExistsToken("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0cnVuZyIsImV4cCI6MTU3ODI3NTc3MX0.ByhxksdFomfJdcx4pUGQZgaWMRIbreqpmYwRpbOo2QE95BH1k4EQCraTJDGA4bXgT28z6-hQZOcevkFR6vLigg");
       return "hello email";
    }

    @GetMapping("/login")
    public String login() {
        return "login success";
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

//    @GetMapping("/logouts")
//    public String logOut(){
//        return
//                "loglout success";
//    }


    @GetMapping("/logouts")
    public String logOut(@RequestHeader Map<String, String> headers) {
        String token = headers.get("authorization").replace(Constants.TOKEN_PREFIX,"");
        BlackToken blackToken = new BlackToken();
        blackToken.setBlackToken(token);
        blackTokenService.save(blackToken);
        return "success123";

    }
}





