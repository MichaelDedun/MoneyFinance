package com.dedun.validator;

import com.dedun.exception.MoneyErrorCode;
import com.dedun.exception.MoneyException;
import com.dedun.model.Loss;
import com.dedun.model.enums.State;
import org.springframework.stereotype.Service;

@Service
public class LossValidator {
    public void checkLossIsInactive(Loss loss) throws MoneyException {
        if (loss.getState().equals(State.INACTIVE))
            throw new MoneyException(MoneyErrorCode.LOSS_NOT_ACTIVE);
    }

    public void checkLossIsActive(Loss loss) throws MoneyException {
        if (loss.getState().equals(State.ACTIVE))
            throw new MoneyException(MoneyErrorCode.LOSS_ALREADY_ACTIVE);
    }
}
