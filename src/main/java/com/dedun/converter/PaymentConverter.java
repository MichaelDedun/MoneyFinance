package com.dedun.converter;

import com.dedun.dto.response.PaymentResponse;
import com.dedun.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentConverter extends MoneyEntityConverter<Payment, PaymentResponse>  {

    public PaymentConverter() {
        super(PaymentResponse::new);
    }

    @Override
    protected PaymentResponse inflateResponse(Payment payment, PaymentResponse paymentResponse) {
        return paymentResponse
                .setId(payment.getId())
                .setDate(payment.getDate())
                .setMoneyCount(payment.getMoneyCount())
                .setType(payment.getType())
                .setGoalId(payment.getGoalId());
    }
}
