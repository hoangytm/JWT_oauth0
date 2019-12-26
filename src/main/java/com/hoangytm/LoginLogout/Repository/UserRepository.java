package com.hoangytm.LoginLogour.Repository;

import com.hoangytm.LoginLogour.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String email);


}
