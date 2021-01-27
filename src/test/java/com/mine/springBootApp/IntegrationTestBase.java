package com.mine.springBootApp;

import com.mine.springBootApp.initializer.Postgres;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@Sql("/sql/data.sql")
@ActiveProfiles("test")
@ContextConfiguration(initializers = {Postgres.Initializer.class})
@SpringBootTest
@Transactional // Для того, чтобы тестовые данные откатывались после тестов, а не оставались в БД
public abstract class IntegrationTestBase {

    @BeforeAll
    static void init() {
        Postgres.container.start();
    }
}
