package com.dedun.validator;

import com.dedun.dto.request.UserRequest;
import com.dedun.exception.MoneyErrorCode;
import com.dedun.exception.MoneyException;
import com.dedun.model.User;
import com.dedun.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserValidator {
    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void checkUserAlreadyExist(UserRequest userRequest) throws MoneyException {
        if (userRepository.getByLogin(userRequest.getLogin()) != null)
            throw new MoneyException(MoneyErrorCode.USER_EXIST);
    }

    public void checkUserExist(User user) throws MoneyException {
        if (user == null)
            throw new MoneyException(MoneyErrorCode.USER_NOT_EXIST);
    }
}
