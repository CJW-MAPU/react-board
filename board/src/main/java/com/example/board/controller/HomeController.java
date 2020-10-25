package com.example.board.controller;

import com.example.board.model.dto.UserDto;
import com.example.board.model.network.ExpansionFiled;
import com.example.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class HomeController {
    @Autowired
    UserService userService;

    @PostMapping("/sign-up")
    public ExpansionFiled<UserDto> create(@RequestBody ExpansionFiled<UserDto> request) {
        return userService.register(request.getData().toEntity());
    }

    @PostMapping("/sign-in")
    public ExpansionFiled<UserDto> read(@RequestBody ExpansionFiled<UserDto> request) {
        return userService.login(request.getData().toEntity());
    }
}
