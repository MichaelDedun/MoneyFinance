package com.dedun.repository;

import com.dedun.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    Optional<Wallet> findByUserIdAndId(int userId, int id);

    List<Wallet> findAllByUserId(int id);

}
