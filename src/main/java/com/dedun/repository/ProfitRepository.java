package com.dedun.repository;


import com.dedun.model.Profit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProfitRepository extends JpaRepository<Profit, Integer> {
    Optional<Profit> findByWalletUserIdAndId(int userId, int id);

    List<Profit> findAllByWalletUserId(int id);

    List<Profit> findByWalletId(int id);
}
