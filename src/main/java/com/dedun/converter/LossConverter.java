package com.dedun.converter;

import com.dedun.dto.response.LossResponse;
import com.dedun.model.Loss;
import org.springframework.stereotype.Service;

@Service
public class LossConverter extends MoneyEntityConverter<Loss, LossResponse> {
    protected LossConverter() {
        super(LossResponse::new);
    }

    @Override
    protected LossResponse inflateResponse(Loss loss, LossResponse lossResponse) {
        return lossResponse
                .setId(loss.getId())
                .setMoneyCount(loss.getMoneyCount())
                .setDate(loss.getDate());

    }
}
