package com.hoangytm.LoginLogout.Config;


import com.auth0.jwt.JWT;

import com.hoangytm.LoginLogout.Model.User;
import com.hoangytm.LoginLogout.Model.UserPrincipal;
import com.hoangytm.LoginLogout.Repository.UserRepository;
import com.hoangytm.LoginLogout.Service.BlackTokenService;
import com.hoangytm.LoginLogout.Service.BlackTokenServiceImpl;
import com.hoangytm.LoginLogout.Util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private UserRepository userRepository;

    @Autowired
    private BlackTokenService blackTokenService;




    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository, BlackTokenService blackTokenService) {
        super(authenticationManager);
        this.userRepository = userRepository;
        this.blackTokenService = blackTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Read the Authorization header, where the JWT token should be
        String header = request.getHeader(Constants.HEADER_STRING);


        // If header does not contain BEARER or is null delegate to Spring impl and exit
        if (header == null || !header.startsWith(Constants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        // If header is present, try grab user principal from database and perform authorization
        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Continue filter execution
        chain.doFilter(request, response);
    }


    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        String token = request.getHeader(Constants.HEADER_STRING)
                .replace(Constants.TOKEN_PREFIX,""  );
//        BlackTokenService blackTokenService = new BlackTokenServiceImpl();
        Boolean isBlackToken= blackTokenService.checkExistsToken(token);
        if ((token != null)&& (!isBlackToken)) {
            // parse the token and validate it
            String userName = JWT.require(HMAC512(Constants.SECRET.getBytes()))
                    .build()
                    .verify(token)
                    .getSubject();

            // Search in the DB if we find the user by token subject (username)
            // If so, then grab user details and create spring auth token using username, pass, authorities/roles
            if (userName != null) {
                User user = userRepository.findByUsername(userName);
                UserPrincipal principal = new UserPrincipal(user);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userName, null, principal.getAuthorities());
                return auth;
            }
            return null;
        }
        return null;
    }
}
