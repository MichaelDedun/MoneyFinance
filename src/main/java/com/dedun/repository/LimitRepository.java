package com.dedun.repository;

import com.dedun.model.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Integer> {
    List<Limit> findAllByWalletUserId(int id);
}
