package com.hoangytm.LoginLogout.Repository;

import com.hoangytm.LoginLogout.Model.BlackToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlackTokenRepository extends JpaRepository<BlackToken,String> {
    @Query(value = "SELECT * FROM black_token b WHERE b.token = :token",
            nativeQuery = true)
    List<BlackToken> findBlackToken(
            @Param("token") String token);
}
