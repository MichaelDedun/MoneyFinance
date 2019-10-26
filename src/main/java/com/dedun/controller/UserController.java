package com.dedun.controller;

import com.dedun.converter.UserConverter;
import com.dedun.dto.request.UserRequest;
import com.dedun.dto.response.UserResponse;
import com.dedun.exception.MoneyException;
import com.dedun.model.User;
import com.dedun.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserConverter userConverter;

    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @PostMapping("registration")
    public UserResponse registerUser(@RequestBody UserRequest userRequest) throws MoneyException {
        return userConverter.from(userService.saveUser(userRequest));
    }

    @GetMapping(value = "{id}")
    public UserResponse getUserById(@PathVariable(value = "id") int id) {
        return userConverter.from(userService.getUserById(id));
    }

    @PutMapping()
    public UserResponse editUser(@AuthenticationPrincipal User user,
                                 @RequestBody UserRequest request) throws MoneyException {
        return userConverter.from(userService.editUser(user,request));
    }
}
