package vn.iotstar.jwt_spring.model;

import lombok.Data;

@Data
public class LoginUserModel {
    private String email;
    private String password;
}