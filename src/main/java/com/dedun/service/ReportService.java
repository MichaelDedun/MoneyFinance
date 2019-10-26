package com.dedun.service;

import com.dedun.dto.request.ReportRequest;
import com.dedun.exception.MoneyErrorCode;
import com.dedun.exception.MoneyException;
import com.dedun.model.*;
import com.dedun.repository.LimitRepository;
import com.dedun.repository.LossRepository;
import com.dedun.repository.ProfitRepository;
import com.dedun.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final ProfitRepository profitRepository;
    private final LossRepository lossRepository;
    private final LimitRepository limitRepository;

    public ReportService(ReportRepository reportRepository,
                         ProfitRepository profitRepository,
                         LossRepository lossRepository,
                         LimitRepository limitRepository) {
        this.reportRepository = reportRepository;
        this.profitRepository = profitRepository;
        this.lossRepository = lossRepository;
        this.limitRepository = limitRepository;
    }

    public Report create(User user, ReportRequest reportRequest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateStart = LocalDateTime.parse(reportRequest.getDateStart() + " 00:00:00", formatter);
        LocalDateTime dateEnd = LocalDateTime.parse(reportRequest.getDateEnd() + " 00:00:00", formatter);
        List<Profit> profits = profitRepository.findAllByWalletUserId(user.getId())
                .stream()
                .filter(profit -> profit.getDate().isAfter(dateStart) && profit.getDate().isBefore(dateEnd))
                .collect(Collectors.toList());
        List<Loss> losses = lossRepository.findAllByWalletUserId(user.getId())
                .stream()
                .filter(loss -> loss.getDate().isAfter(dateStart) && loss.getDate().isBefore(dateEnd))
                .collect(Collectors.toList());
        List<Limit> limits = limitRepository.findAllByWalletUserId(user.getId())
                .stream()
                .filter(limit -> limit.getDateFrom().isAfter(dateStart) || limit.getDateFrom().isEqual(dateStart)
                        && limit.getDateTo().isBefore(dateEnd) || limit.getDateTo().isEqual(dateEnd))
                .collect(Collectors.toList());
        Report report = new Report(user, dateStart, dateEnd, profits, losses, limits);
        reportRepository.save(report);
        return report;
    }

    public List<Report> getAll(int id) {
        return reportRepository.findAllByUserId(id);
    }

    public Report get(int userid, int reportId) throws MoneyException {
        return reportRepository.findByUserIdAndId(userid, reportId)
                .orElseThrow(() -> new MoneyException(MoneyErrorCode.REPORT_NOT_EXIST));
    }
}
