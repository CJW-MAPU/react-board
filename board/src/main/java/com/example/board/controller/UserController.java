package com.example.board.controller;

import com.example.board.model.network.ExpansionFiled;
import com.example.board.model.dto.UserDto;
import com.example.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PutMapping
    public ExpansionFiled<UserDto> update(@RequestBody ExpansionFiled<UserDto> request) {
        return userService.update(request.getData().toEntity());
    }

    @DeleteMapping("/{username}")
    public ExpansionFiled<UserDto> delete(@PathVariable("username") String username) {
        return userService.delete(username);
    }
}
