package com.hoangytm.LoginLogout;

import com.hoangytm.LoginLogout.Service.BlackTokenService;
import org.springframework.beans.factory.annotation.Autowired;

public class TestFunction {
    @Autowired
    BlackTokenService blackTokenService ;

    public void check(){

        Boolean a= blackTokenService.checkExistsToken("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0cnVuZyIsImV4cCI6MTU3ODI3NTc3MX0.ByhxksdFomfJdcx4pUGQZgaWMRIbreqpmYwRpbOo2QE95BH1k4EQCraTJDGA4bXgT28z6-hQZOcevkFR6vLigg");
    }


}
