package com.example.devup_backend.domain.user.repository;

import com.example.devup_backend.domain.user.model.field.Field;
import com.example.devup_backend.domain.user.model.field.FieldId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<Field, FieldId> {

}
