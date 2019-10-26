package com.dedun.repository;


import com.dedun.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
    Optional<Report> findByUserIdAndId(int userId, int id);

    List<Report> findAllByUserId(int id);
}
