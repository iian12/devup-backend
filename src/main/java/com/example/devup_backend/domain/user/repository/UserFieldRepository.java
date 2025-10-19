package com.example.devup_backend.domain.user.repository;

import com.example.devup_backend.domain.user.model.UserField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFieldRepository extends JpaRepository<UserField, String> {
}
