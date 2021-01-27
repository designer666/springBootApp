package com.mine.springBootApp.repository;

import com.mine.springBootApp.IntegrationTestBase;
import com.mine.springBootApp.entity.CompanyEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CompanyRepositoryTest extends IntegrationTestBase {

    private static final Integer SONY_ID = 1;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetById() {
        Optional<CompanyEntity> company = companyRepository.findById(SONY_ID);
        assertTrue(company.isPresent());
        company.ifPresent(entity -> {
            assertEquals("Sony", entity.getName());
        });
    }

    @Test
    void testSave() {
        CompanyEntity company = CompanyEntity.builder().name("Anyone").build();
//        .builder() - можем использовать благодаря аннотации @Builder в public class CompanyEntity() {}
        companyRepository.save(company);
        assertNotNull(company.getId());
    }
}