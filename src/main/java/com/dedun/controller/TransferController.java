package com.dedun.controller;


import com.dedun.converter.TransferConverter;
import com.dedun.dto.request.TransferRequest;
import com.dedun.dto.response.TransferResponse;
import com.dedun.exception.MoneyException;
import com.dedun.model.User;
import com.dedun.service.TransferService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    private final TransferService transferService;
    private final TransferConverter transferConverter;

    public TransferController(TransferService transferService, TransferConverter transferConverter) {
        this.transferService = transferService;
        this.transferConverter = transferConverter;
    }

    @PostMapping
    public TransferResponse createNewTransfer(@AuthenticationPrincipal User user,
                                              @RequestBody @Valid TransferRequest request) throws MoneyException {
        return transferConverter.from(transferService.saveTransfer(user,request));
    }

}
