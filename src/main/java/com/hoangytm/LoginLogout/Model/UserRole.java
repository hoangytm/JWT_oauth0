package com.hoangytm.LoginLogout.Model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_role_id")
    private Integer userRoleID;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "role")
    private String role;

}
