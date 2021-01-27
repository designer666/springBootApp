package com.mine.springBootApp.repository;

import com.mine.springBootApp.IntegrationTestBase;
import com.mine.springBootApp.dto.EmployeeFilter;
import com.mine.springBootApp.entity.EmployeeEntity;
import com.mine.springBootApp.projection.EmployeeNameView;
import com.mine.springBootApp.projection.EmployeeNativeView;
import com.mine.springBootApp.util.QPredicates;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.mine.springBootApp.entity.QEmployeeEntity.employeeEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeRepositoryTest extends IntegrationTestBase {

    private static final Integer FIRST_ID = 1;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testFindById() {
        Optional<EmployeeEntity> employee = employeeRepository.findById(FIRST_ID);
        assertTrue(employee.isPresent());
    }

    @Test
    void testFindByFirstName() {
        Optional<EmployeeEntity> employee = employeeRepository.findByFirstNameContaining("Al");
        assertTrue(employee.isPresent());
    }

    @Test
    void testFindAllByFirstNameAndSalary() {
        List<EmployeeEntity> employees = employeeRepository.findAllByFirstNameAndSalary("Alex", 1000);
        assertThat(employees, IsCollectionWithSize.hasSize(1));
    }

    @Test
    void testFindAllBySalaryGreaterThan() {
        List<EmployeeNameView> employees = employeeRepository.findAllBySalaryGreaterThan(400);
        assertThat(employees, hasSize(2));
    }

    @Test
    void testFindAllBySalaryGreaterThanNative() {
        List<EmployeeNativeView> employees = employeeRepository.findAllBySalaryGreaterThanNative(400);
        assertThat(employees, hasSize(2));
    }

    @Test
    void testFindCustomQuery() {
        EmployeeFilter filter = EmployeeFilter.builder().firstName("alex").build();
        List<EmployeeEntity> customQuery = employeeRepository.findByFilter(filter);
        assertThat(customQuery, hasSize(1));
    }

    @Test
    void testQuerydslPredicates() {
        BooleanExpression predicate = employeeEntity.firstName.containsIgnoreCase("alex").and(employeeEntity.salary.goe(1000));
        Page<EmployeeEntity> allValues = employeeRepository.findAll(predicate, Pageable.unpaged());
        assertThat(allValues.getContent(), hasSize(1));
    }

    @Test
    void testQPredicates() {
        EmployeeFilter filter = EmployeeFilter.builder().firstName("alex").salary(1000).build();
        Predicate predicate = QPredicates.builder().add(filter.getFirstName(), employeeEntity.firstName::containsIgnoreCase).add(filter.getSecondName(), employeeEntity.secondName::containsIgnoreCase).add(filter.getSalary(), employeeEntity.salary::goe).buildAnd();
        Iterable<EmployeeEntity> result = employeeRepository.findAll(predicate);
        assertTrue(result.iterator().hasNext());
        System.out.println();
    }
}