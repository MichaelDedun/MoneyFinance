package com.dedun.controller;

import com.dedun.converter.PaymentConverter;
import com.dedun.dto.response.PaymentResponse;
import com.dedun.exception.MoneyException;
import com.dedun.model.User;
import com.dedun.service.PaymentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentConverter paymentConverter;

    public PaymentController(PaymentService paymentService, PaymentConverter paymentConverter) {
        this.paymentService = paymentService;
        this.paymentConverter = paymentConverter;
    }

    @GetMapping(value = "{id}")
    public List<PaymentResponse> getPaymentsByWallet(@AuthenticationPrincipal User user,
                                             @PathVariable(value = "id") int id) throws MoneyException {
        return paymentService.getAllByWallet(id).stream().map(paymentConverter::from).collect(Collectors.toList());
    }
}
