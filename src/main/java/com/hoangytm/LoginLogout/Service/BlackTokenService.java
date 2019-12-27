package com.hoangytm.LoginLogout.Service;


import com.hoangytm.LoginLogout.Model.BlackToken;

public interface BlackTokenService {
    public BlackToken save(BlackToken blackToken);
    public Boolean checkExistsToken(String blackToken);

}
