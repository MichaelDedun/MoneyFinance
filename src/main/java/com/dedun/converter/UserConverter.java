package com.dedun.converter;

import com.dedun.dto.response.UserResponse;
import com.dedun.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserConverter extends MoneyEntityConverter<User, UserResponse> {
    protected UserConverter() {
        super(UserResponse::new);
    }

    @Override
    protected UserResponse inflateResponse(User user, UserResponse userResponse) {
        return userResponse
                .setId(user.getId())
                .setLogin(user.getLogin())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setPatronymic(user.getPatronymic())
                .setEmail(user.getEmail());
    }
}
