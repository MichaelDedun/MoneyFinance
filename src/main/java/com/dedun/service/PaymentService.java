package com.dedun.service;

import com.dedun.exception.MoneyErrorCode;
import com.dedun.exception.MoneyException;
import com.dedun.model.Loss;
import com.dedun.model.Payment;
import com.dedun.model.Profit;
import com.dedun.model.enums.PaymentType;
import com.dedun.repository.PaymentRepository;
import com.dedun.validator.PaymentValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentValidator paymentValidator;

    public PaymentService(PaymentRepository paymentRepository,
                          PaymentValidator paymentValidator) {
        this.paymentRepository = paymentRepository;
        this.paymentValidator = paymentValidator;
    }

    public void save(Profit profit) {
        Payment payment = new Payment(profit.getId(), profit.getDate(), profit.getMoneyCount(), profit.getWallet());
        payment.setType(PaymentType.CREATE_PROFIT);
        paymentRepository.save(payment);
    }

    public void edit(Profit profit) throws MoneyException {
        Payment parentPayment = paymentRepository.findByWalletIdAndGoalIdAndTypeAndParentPaymentIsNull(profit.getWallet().getId(), profit.getId(), PaymentType.CREATE_PROFIT)
                .orElseThrow(() -> new MoneyException(MoneyErrorCode.PAYMENT_NOT_EXIST));
        Payment correctingPayment = new Payment(profit.getId(), profit.getDate(), profit.getMoneyCount(), profit.getWallet());
        correctingPayment.setType(PaymentType.EDIT_PROFIT);
        correctingPayment.setParentPayment(parentPayment);
        paymentRepository.save(correctingPayment);
    }

    public void save(Loss loss) {
        Payment payment = new Payment(loss.getId(), loss.getDate(), loss.getMoneyCount(), loss.getWallet());
        payment.setType(PaymentType.CREATE_LOSS);
        paymentRepository.save(payment);
    }

    public void edit(Loss loss) throws MoneyException {
        Payment parentPayment = paymentRepository.findByWalletIdAndGoalIdAndTypeAndParentPaymentIsNull(loss.getWallet().getId(), loss.getId(), PaymentType.CREATE_LOSS)
                .orElseThrow(() -> new MoneyException(MoneyErrorCode.PAYMENT_NOT_EXIST));
        Payment correctingPayment = new Payment(loss.getId(), loss.getDate(), loss.getMoneyCount(), loss.getWallet());
        correctingPayment.setType(PaymentType.EDIT_LOSS);
        correctingPayment.setParentPayment(parentPayment);
        paymentRepository.save(correctingPayment);
    }

    public List<Payment> getAllByWallet(int walletId) throws MoneyException {
        return paymentRepository.findAllByWalletIdAndParentPaymentIsNull(walletId)
                .stream().filter(paymentValidator::checkPayment)
                .map(payment -> {
                    if (payment.getCorrectingPayments() != null && !payment.getCorrectingPayments().isEmpty())
                        return payment.getCorrectingPayments().get(payment.getCorrectingPayments().size() - 1);
                    return payment;
                })
                .collect(Collectors.toList());
    }
}
