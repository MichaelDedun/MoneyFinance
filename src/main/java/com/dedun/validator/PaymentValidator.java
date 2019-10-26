package com.dedun.validator;

import com.dedun.model.Payment;
import com.dedun.model.enums.PaymentType;
import com.dedun.model.enums.State;
import com.dedun.repository.LossRepository;
import com.dedun.repository.ProfitRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentValidator {
    private final ProfitRepository profitRepository;
    private final LossRepository lossRepository;

    public PaymentValidator(ProfitRepository profitRepository, LossRepository lossRepository) {
        this.profitRepository = profitRepository;
        this.lossRepository = lossRepository;
    }

    public boolean checkPayment(Payment payment) {
        if (payment.getType().equals(PaymentType.CREATE_PROFIT) || payment.getType().equals(PaymentType.EDIT_PROFIT)) {
            return profitRepository.findById(payment.getGoalId()).get().getState().equals(State.ACTIVE);
        } else if (payment.getType().equals(PaymentType.CREATE_LOSS) || payment.getType().equals(PaymentType.EDIT_LOSS)) {
            return lossRepository.findById(payment.getGoalId()).get().getState().equals(State.ACTIVE);
        } else {
            return true;
        }
    }
}
