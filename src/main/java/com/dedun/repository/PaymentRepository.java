package com.dedun.repository;

import com.dedun.model.Payment;
import com.dedun.model.enums.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Optional<Payment> findByWalletIdAndGoalIdAndTypeAndParentPaymentIsNull(int walletId, int goalId, PaymentType type);

    List<Payment> findAllByWalletIdAndParentPaymentIsNull(int id);
}
