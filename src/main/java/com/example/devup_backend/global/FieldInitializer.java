package com.example.devup_backend.global;

import com.example.devup_backend.domain.user.model.Field;
import com.example.devup_backend.domain.user.repository.FieldRepository;
import io.hypersistence.utils.hibernate.id.TsidGenerator;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class FieldInitializer {

    @Bean
    @Transactional
    public ApplicationRunner initFields(FieldRepository fieldRepository) {
        return args -> {
            if (fieldRepository.count() > 0) return;

            // 대분류 생성
            Field backend = Field.builder().name("백엔드").parentId(null).build();
            Field frontend = Field.builder().name("프론트엔드").parentId(null).build();
            Field mobile = Field.builder().name("모바일").parentId(null).build();
            Field devops = Field.builder().name("데브옵스").parentId(null).build();
            Field data = Field.builder().name("데이터").parentId(null).build();
            Field ai = Field.builder().name("AI").parentId(null).build();
            Field embedded = Field.builder().name("임베디드").parentId(null).build();
            Field game = Field.builder().name("게임").parentId(null).build();
            Field security = Field.builder().name("보안").parentId(null).build();
            Field architecture = Field.builder().name("아키텍처").parentId(null).build();
            Field qa = Field.builder().name("QA").parentId(null).build();
            Field designer = Field.builder().name("디자이너").parentId(null).build();
            Field planning = Field.builder().name("기획").parentId(null).build();
            Field etc = Field.builder().name("기타").parentId(null).build();

            List<Field> parents = List.of(backend, frontend, mobile, devops, data, ai,
                    embedded, game, security, architecture, qa, designer, planning, etc);

            fieldRepository.saveAll(parents);

            // 하위 필드 생성
            List<Field> children = new ArrayList<>();

            // 백엔드
            children.add(Field.builder().name("Spring").parentId(backend.getId()).build());
            children.add(Field.builder().name("Node.js").parentId(backend.getId()).build());
            children.add(Field.builder().name("Python").parentId(backend.getId()).build());
            children.add(Field.builder().name("C++").parentId(backend.getId()).build());
            children.add(Field.builder().name("C#").parentId(backend.getId()).build());
            children.add(Field.builder().name("Rust").parentId(backend.getId()).build());
            children.add(Field.builder().name("PHP").parentId(backend.getId()).build());
            children.add(Field.builder().name("Go").parentId(backend.getId()).build());
            children.add(Field.builder().name("기타").parentId(backend.getId()).build());

            // 프론트엔드
            children.add(Field.builder().name("React.js").parentId(frontend.getId()).build());
            children.add(Field.builder().name("Vue.js").parentId(frontend.getId()).build());
            children.add(Field.builder().name("Angular").parentId(frontend.getId()).build());
            children.add(Field.builder().name("Svelte").parentId(frontend.getId()).build());
            children.add(Field.builder().name("기타").parentId(frontend.getId()).build());

            // 모바일
            children.add(Field.builder().name("Android").parentId(mobile.getId()).build());
            children.add(Field.builder().name("iOS").parentId(mobile.getId()).build());
            children.add(Field.builder().name("Flutter").parentId(mobile.getId()).build());
            children.add(Field.builder().name("React Native").parentId(mobile.getId()).build());
            children.add(Field.builder().name("기타").parentId(mobile.getId()).build());

            // 데브옵스
            children.add(Field.builder().name("CI/CD").parentId(devops.getId()).build());
            children.add(Field.builder().name("컨테이너").parentId(devops.getId()).build());
            children.add(Field.builder().name("클라우드").parentId(devops.getId()).build());
            children.add(Field.builder().name("기타").parentId(devops.getId()).build());

            // 데이터
            children.add(Field.builder().name("데이터베이스").parentId(data.getId()).build());
            children.add(Field.builder().name("데이터 분석").parentId(data.getId()).build());
            children.add(Field.builder().name("데이터 엔지니어링").parentId(data.getId()).build());
            children.add(Field.builder().name("기타").parentId(data.getId()).build());

            // AI
            children.add(Field.builder().name("머신러닝").parentId(ai.getId()).build());
            children.add(Field.builder().name("딥러닝").parentId(ai.getId()).build());
            children.add(Field.builder().name("자연어 처리").parentId(ai.getId()).build());
            children.add(Field.builder().name("컴퓨터 비전").parentId(ai.getId()).build());
            children.add(Field.builder().name("기타").parentId(ai.getId()).build());

            // 기타 분류는 필요시 추가...

            fieldRepository.saveAll(children);

            log.info("✅ Field 초기 데이터가 성공적으로 생성되었습니다!");
        };
    }
}
