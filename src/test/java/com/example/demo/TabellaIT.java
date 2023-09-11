package com.example.demo;

import com.example.demo.entity.TabellaEntity;
import com.example.demo.repository.TabellaJpaRepository;
import com.example.demo.service.TabellaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TabellaIT
{
    @Autowired
    private TabellaService testSubject;
    @Autowired
    private TabellaJpaRepository repository;

    @BeforeEach
    public void logAllEntities() {
        List<TabellaEntity> entities = repository.findAll();
        log.info("---> DATABASE STARTUP --->");
        log.info("The following entities are initially present");
        entities.forEach(e -> log.info(e.toString()));
        log.info("<--- DATABASE STARTUP <---");
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    @Sql(scripts = "classpath:/testsql/save.sql")
    @DisplayName("Save REQUIRED - Test method is @Transactional")
    @Order(2)
    void save_REQUIRED_TestIsTransactional() {
        UUID id1 = UUID.fromString("2afd47ac-cd75-4389-b6c5-d84dd2966fc0"); // id di Pippo
        UUID id2 = UUID.randomUUID();
        TabellaEntity entity = builTabellaTestEntity(id2, "Topolino", "Record saved through testSubject");

        testSubject.save(entity);

        List<TabellaEntity> entities = repository.findAll();

        assertThat(entities.size()).isEqualTo(2);
        TabellaEntity entity1 = entities.get(0);
        assertThat(entity1.getId()).isEqualTo(id1);
        assertThat(entity1.getName()).isEqualTo("Pippo");
        assertThat(entity1.getDescription()).isEqualTo("Record saved through script");

        TabellaEntity entity2 = entities.get(1);
        assertThat(entity2.getId()).isEqualTo(id2);
        assertThat(entity2.getName()).isEqualTo("Topolino");
        assertThat(entity2.getDescription()).isEqualTo("Record saved through testSubject");
    }

//    @Test
    @Sql(scripts = "classpath:/testsql/save.sql")
    @DisplayName("Save REQUIRED - Test method is NOT @Transactional")
    @Order(2)
    void save_REQUIRED_TestIsNotTransactional() {
        UUID id1 = UUID.fromString("2afd47ac-cd75-4389-b6c5-d84dd2966fc0"); // id di Pippo
        UUID id2 = UUID.randomUUID();
        TabellaEntity entity = builTabellaTestEntity(id2, "Topolino", "Record saved through testSubject");

        testSubject.save(entity);

        List<TabellaEntity> entities = repository.findAll();

        assertThat(entities.size()).isEqualTo(2);
        TabellaEntity entity1 = entities.get(0);
        assertThat(entity1.getId()).isEqualTo(id1);
        assertThat(entity1.getName()).isEqualTo("Pippo");
        assertThat(entity1.getDescription()).isEqualTo("Record saved through script");

        TabellaEntity entity2 = entities.get(1);
        assertThat(entity2.getId()).isEqualTo(id2);
        assertThat(entity2.getName()).isEqualTo("Topolino");
        assertThat(entity2.getDescription()).isEqualTo("Record saved through testSubject");
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    @Sql(scripts = "classpath:/testsql/save.sql")
    @DisplayName("Save REQUIRES_NEW - Test method is @Transactional")
    @Order(1)
    void save_REQUIRES_NEW_TestIsTransactional() {
        UUID id1 = UUID.fromString("2afd47ac-cd75-4389-b6c5-d84dd2966fc0"); // id di Pippo
        UUID id2 = UUID.randomUUID();
        TabellaEntity entity = builTabellaTestEntity(id2, "Topolino", "Record saved through testSubject");

        testSubject.saveRequiresNew(entity);

        List<TabellaEntity> entities = repository.findAll();

        assertThat(entities.size()).isEqualTo(2);
        TabellaEntity entity1 = entities.get(0);
        assertThat(entity1.getId()).isEqualTo(id1);
        assertThat(entity1.getName()).isEqualTo("Pippo");
        assertThat(entity1.getDescription()).isEqualTo("Record saved through script");

        TabellaEntity entity2 = entities.get(1);
        assertThat(entity2.getId()).isEqualTo(id2);
        assertThat(entity2.getName()).isEqualTo("Topolino");
        assertThat(entity2.getDescription()).isEqualTo("Record saved through testSubject");
    }

//    @Test
    @Sql(scripts = "classpath:/testsql/save.sql")
    @DisplayName("Save REQUIRES_NEW - Test method is NOT @Transactional")
    @Order(4)
    void save_REQUIRES_NEW_TestIsNotTransactional() {
        UUID id1 = UUID.fromString("2afd47ac-cd75-4389-b6c5-d84dd2966fc0"); // id di Pippo
        UUID id2 = UUID.randomUUID();
        TabellaEntity entity = builTabellaTestEntity(id2, "Topolino", "Record saved through testSubject");

        testSubject.saveRequiresNew(entity);

        List<TabellaEntity> entities = repository.findAll();

        assertThat(entities.size()).isEqualTo(2);
        TabellaEntity entity1 = entities.get(0);
        assertThat(entity1.getId()).isEqualTo(id1);
        assertThat(entity1.getName()).isEqualTo("Pippo");
        assertThat(entity1.getDescription()).isEqualTo("Record saved through script");

        TabellaEntity entity2 = entities.get(1);
        assertThat(entity2.getId()).isEqualTo(id2);
        assertThat(entity2.getName()).isEqualTo("Topolino");
        assertThat(entity2.getDescription()).isEqualTo("Record saved through testSubject");
    }

    private TabellaEntity builTabellaTestEntity(UUID id, String name, String description) {
        return TabellaEntity.builder().id(id).name(name).description(description).build();
    }
}
