package com.dedun.converter;

import com.dedun.dto.response.ProfitResponse;
import com.dedun.model.Profit;
import org.springframework.stereotype.Service;

@Service
public class ProfitConverter extends MoneyEntityConverter<Profit, ProfitResponse> {

    protected ProfitConverter() {
        super(ProfitResponse::new);
    }

    @Override
    protected ProfitResponse inflateResponse(Profit profit, ProfitResponse profitResponse) {
        return profitResponse
                .setId(profit.getId())
                .setMoneyCount(profit.getMoneyCount())
                .setDate(profit.getDate());
    }
}
