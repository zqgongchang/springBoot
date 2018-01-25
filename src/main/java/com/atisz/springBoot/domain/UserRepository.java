package com.atisz.springBoot.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String userName);
    User findByNameOrEmail(String username, String email);

    List<User> findByEmailLike(String email);
}
