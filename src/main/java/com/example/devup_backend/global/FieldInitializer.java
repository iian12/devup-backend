package com.example.devup_backend.global;

import com.example.devup_backend.domain.user.model.field.Field;
import com.example.devup_backend.domain.user.model.field.FieldId;
import com.example.devup_backend.domain.user.repository.FieldRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/*
    애플리케이션 시작 시 필드 데이터를 초기화하는 설정 클래스입니다.
    데이터베이스에 필드 데이터가 없을 경우, 미리 정의된 대분류 및 하위 필드 데이터를 삽입합니다.
*/
@Slf4j
@Configuration
public class FieldInitializer {

    @Bean
    @Transactional
    public ApplicationRunner initFields(FieldRepository fieldRepository) {
        return args -> {
            if (fieldRepository.count() > 0) return;

            // === 대분류 (고정 ID 부여) ===
            Field backend = Field.builder().fieldId(FieldId.of(1L)).name("백엔드").parentId(null).build();
            Field frontend = Field.builder().fieldId(FieldId.of(2L)).name("프론트엔드").parentId(null).build();
            Field mobile = Field.builder().fieldId(FieldId.of(3L)).name("모바일").parentId(null).build();
            Field devops = Field.builder().fieldId(FieldId.of(4L)).name("데브옵스").parentId(null).build();
            Field data = Field.builder().fieldId(FieldId.of(5L)).name("데이터").parentId(null).build();
            Field ai = Field.builder().fieldId(FieldId.of(6L)).name("AI").parentId(null).build();
            Field embedded = Field.builder().fieldId(FieldId.of(7L)).name("임베디드").parentId(null).build();
            Field game = Field.builder().fieldId(FieldId.of(8L)).name("게임").parentId(null).build();
            Field security = Field.builder().fieldId(FieldId.of(9L)).name("보안").parentId(null).build();
            Field architecture = Field.builder().fieldId(FieldId.of(10L)).name("아키텍처").parentId(null).build();
            Field qa = Field.builder().fieldId(FieldId.of(11L)).name("QA").parentId(null).build();
            Field designer = Field.builder().fieldId(FieldId.of(12L)).name("디자이너").parentId(null).build();
            Field planning = Field.builder().fieldId(FieldId.of(13L)).name("기획").parentId(null).build();
            Field etc = Field.builder().fieldId(FieldId.of(14L)).name("기타").parentId(null).build();

            List<Field> parents = List.of(
                    backend, frontend, mobile, devops, data, ai,
                    embedded, game, security, architecture, qa, designer, planning, etc
            );

            fieldRepository.saveAll(parents);

            // === 하위 필드 생성 ===
            List<Field> children = new ArrayList<>();
            long childId = 100L; // 하위 ID 시작 번호

            // 백엔드
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("Spring").parentId(backend.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("Node.js").parentId(backend.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("Python").parentId(backend.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("C++").parentId(backend.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("C#").parentId(backend.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("Rust").parentId(backend.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("PHP").parentId(backend.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("Go").parentId(backend.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("기타").parentId(backend.getFieldId().value()).build());

// 프론트엔드
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("React.js").parentId(frontend.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("Vue.js").parentId(frontend.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("Angular").parentId(frontend.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("Svelte").parentId(frontend.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("기타").parentId(frontend.getFieldId().value()).build());

// 모바일
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("Android").parentId(mobile.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("iOS").parentId(mobile.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("Flutter").parentId(mobile.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("React Native").parentId(mobile.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("기타").parentId(mobile.getFieldId().value()).build());

// 데브옵스
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("CI/CD").parentId(devops.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("컨테이너").parentId(devops.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("클라우드").parentId(devops.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("기타").parentId(devops.getFieldId().value()).build());

// 데이터
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("데이터베이스").parentId(data.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("데이터 분석").parentId(data.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("데이터 엔지니어링").parentId(data.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("기타").parentId(data.getFieldId().value()).build());

// AI
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("머신러닝").parentId(ai.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("딥러닝").parentId(ai.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("자연어 처리").parentId(ai.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("컴퓨터 비전").parentId(ai.getFieldId().value()).build());
            children.add(Field.builder().fieldId(FieldId.of(childId++)).name("기타").parentId(ai.getFieldId().value()).build());

            fieldRepository.saveAll(children);

            log.info("✅ Field 초기 데이터가 성공적으로 생성되었습니다!");
        };
    }
}
