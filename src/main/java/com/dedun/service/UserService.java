package com.dedun.service;

import com.dedun.dto.request.UserRequest;
import com.dedun.dto.response.UserResponse;
import com.dedun.exception.MoneyException;
import com.dedun.model.User;
import com.dedun.model.enums.Role;
import com.dedun.repository.UserRepository;
import com.dedun.validator.UserValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidator userValidator;

    public UserService(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User getUserById(int id) {
        return userRepository.getById(id);
    }

    public User saveUser(UserRequest userRequest) throws MoneyException {
        userValidator.checkUserAlreadyExist(userRequest);
        User user = new User(userRequest.getLogin(), null, userRequest.getFirstName(), userRequest.getLastName(), userRequest.getPatronymic(), userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return user;
    }

    public User editUser(User user, UserRequest request) throws MoneyException {
        userValidator.checkUserExist(user);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPatronymic(request.getPatronymic());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.getByLogin(login);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        return user;
    }
}
