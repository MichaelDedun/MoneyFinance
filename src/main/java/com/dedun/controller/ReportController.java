package com.dedun.controller;


import com.dedun.converter.ReportConverter;
import com.dedun.dto.request.ReportRequest;
import com.dedun.dto.response.ReportResponse;
import com.dedun.exception.MoneyException;
import com.dedun.model.User;
import com.dedun.service.ReportService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;
    private final ReportConverter reportConverter;

    public ReportController(ReportService reportService, ReportConverter reportConverter) {
        this.reportService = reportService;
        this.reportConverter = reportConverter;
    }

    @PostMapping
    public ReportResponse create(@AuthenticationPrincipal User user, @RequestBody @Valid ReportRequest request) {
        return reportConverter.from(reportService.create(user,request));
    }

    @GetMapping
    public List<ReportResponse> getReports(@AuthenticationPrincipal User user) {
        return reportService.getAll(user.getId()).stream()
                .map(reportConverter::from)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "{id}")
    public ReportResponse get(@PathVariable(value = "id") int reportId,
                              @AuthenticationPrincipal User user) throws MoneyException {
        return reportConverter.from(reportService.get(user.getId(), reportId));
    }
}
