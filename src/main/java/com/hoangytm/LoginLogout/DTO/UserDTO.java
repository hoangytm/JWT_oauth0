package com.hoangytm.LoginLogour.DTO;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

 class UserDto {
    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty
    private String email;

    // standard getters and setters
}