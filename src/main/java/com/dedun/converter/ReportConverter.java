package com.dedun.converter;

import com.dedun.dto.response.ReportResponse;
import com.dedun.model.Report;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ReportConverter extends MoneyEntityConverter<Report, ReportResponse> {
    private final LossConverter lossConverter;
    private final ProfitConverter profitConverter;
    private final LimitConverter limitConverter;

    protected ReportConverter(LossConverter lossConverter,
                              ProfitConverter profitConverter,
                              LimitConverter limitConverter) {
        super(ReportResponse::new);
        this.lossConverter = lossConverter;
        this.profitConverter = profitConverter;
        this.limitConverter = limitConverter;
    }

    @Override
    protected ReportResponse inflateResponse(Report report, ReportResponse reportResponse) {
        return reportResponse
                .setId(report.getId())
                .setDateStart(report.getDateStart())
                .setDateEnd(report.getDateEnd())
                .setProfits(report.getProfits().stream().map(profitConverter::from).collect(Collectors.toList()))
                .setLosses(report.getLosses().stream().map(lossConverter::from).collect(Collectors.toList()))
                .setLimits(report.getLimits().stream().map(limitConverter::from).collect(Collectors.toList()));
    }
}
