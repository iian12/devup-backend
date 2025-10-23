package com.example.devup_backend.domain.user.repository;

import com.example.devup_backend.domain.user.model.field.UserField;
import com.example.devup_backend.domain.user.model.field.UserFieldId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFieldRepository extends JpaRepository<UserField, UserFieldId> {
}
