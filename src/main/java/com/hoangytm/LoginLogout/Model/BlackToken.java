package com.hoangytm.LoginLogout.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class BlackToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(name="Token")
    public String blackToken;
}
