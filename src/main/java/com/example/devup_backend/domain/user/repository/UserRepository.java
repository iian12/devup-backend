package com.example.devup_backend.domain.user.repository;

import com.example.devup_backend.domain.user.model.UserId;
import com.example.devup_backend.domain.user.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, UserId> {

    Optional<Users> findByEmail(String email);

}
