package vn.iotstar.jwt_spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


import vn.iotstar.jwt_spring.entity.User;
import vn.iotstar.jwt_spring.repository.UserRepository;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
}