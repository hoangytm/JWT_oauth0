package com.hoangytm.LoginLogout.Service;

import com.hoangytm.LoginLogout.Model.BlackToken;
import com.hoangytm.LoginLogout.Repository.BlackTokenRepository;
import com.hoangytm.LoginLogout.Util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlackTokenServiceImpl implements BlackTokenService {
    @Autowired
    BlackTokenRepository blackTokenRepository;

    @Override
    public BlackToken save(BlackToken blackToken) {
        return blackTokenRepository.save(blackToken);

    }


    @Override
    public Boolean checkExistsToken(String blackToken) {

        List<BlackToken> bl = blackTokenRepository.findBlackToken(blackToken.replace(Constants.TOKEN_PREFIX,""));
        return bl.size()>0?true:false;
    }


}
