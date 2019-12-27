package com.hoangytm.LoginLogout.Repository;

import com.hoangytm.LoginLogout.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String email);


}
