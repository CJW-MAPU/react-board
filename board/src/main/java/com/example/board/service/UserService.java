package com.example.board.service;

import com.example.board.model.dto.UserDto;
import com.example.board.model.entity.User;
import com.example.board.model.network.ExpansionFiled;
import com.example.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ExpansionFiled<UserDto> register(User user) {
        User userCheck = userRepository.findByUsername(user.getUsername());

        if (userCheck != null) {
            return ExpansionFiled.error(202, "Please check that the Username is duplicated.");
        } else {
            userRepository.save(user);
            return ExpansionFiled.ok(201);
        }
    }

    public ExpansionFiled<UserDto> login(User user) {
        User usernameCheck = userRepository.findByUsername(user.getUsername());
        User passwordCheck = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());

        if (usernameCheck == null) {
            return ExpansionFiled.error(103, "Username is invalid.");
        } else {
            if (passwordCheck == null) {
                return ExpansionFiled.error(102, "Password is invalid.");
            } else {
                UserDto ret = UserDto.builder()
                        .username(passwordCheck.getUsername())
                        .email(passwordCheck.getEmail())
                        .build();

                return ExpansionFiled.ok(ret, 101);
            }
        }
    }
}