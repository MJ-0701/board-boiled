package com.example.blinddate.domain.user.domain.repository;

import com.example.blinddate.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByAccount(String account);

    User findByAccountAndPassword(String account, String password);
}
