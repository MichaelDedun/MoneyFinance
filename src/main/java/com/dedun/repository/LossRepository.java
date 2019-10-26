package com.dedun.repository;

import com.dedun.model.Loss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LossRepository extends JpaRepository<Loss, Integer> {
    Optional<Loss> findByWalletUserIdAndId(int userId, int id);

    List<Loss> findAllByWalletUserId(int id);

    List<Loss> findByWalletId(int id);

}
