package com.dedun.validator;

import com.dedun.exception.MoneyErrorCode;
import com.dedun.exception.MoneyException;
import com.dedun.model.Profit;
import com.dedun.model.enums.State;
import org.springframework.stereotype.Service;

@Service
public class ProfitValidator {
    public void checkProfitIsInactive(Profit profit) throws MoneyException {
        if (profit.getState().equals(State.INACTIVE))
            throw new MoneyException(MoneyErrorCode.PROFIT_NOT_ACTIVE);
    }

    public void checkProfitIsActive(Profit profit) throws MoneyException {
        if (profit.getState().equals(State.ACTIVE))
            throw new MoneyException(MoneyErrorCode.PROFIT_ALREADY_ACTIVE);
    }
}
