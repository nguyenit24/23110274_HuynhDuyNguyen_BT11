package vn.iotstar.jwt_spring.model;

import lombok.Data;

@Data
public class RegisterUserModel {
    private String email;
    private String password;
    private String fullName;
}