package com.example.devup_backend.domain.user.repository;

import com.example.devup_backend.domain.user.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FieldRepository extends JpaRepository<Field, Long> {

}
