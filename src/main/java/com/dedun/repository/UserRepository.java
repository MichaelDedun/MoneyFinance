package com.dedun.repository;

import com.dedun.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getByLogin(String login);

    User getById(int id);

}
