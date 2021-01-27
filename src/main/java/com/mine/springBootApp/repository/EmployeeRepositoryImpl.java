package com.mine.springBootApp.repository;

import com.mine.springBootApp.dto.EmployeeFilter;
import com.mine.springBootApp.entity.EmployeeEntity;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

import java.util.List;

import static com.mine.springBootApp.entity.QEmployeeEntity.employeeEntity;

@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeCustomRepository {

    private final EntityManager entityManager;

    /*@Override
    public List<EmployeeEntity> findCustomQuery() {
        return Collections.emptyList();
    }*/

    @Override
    public List<EmployeeEntity> findByFilter(EmployeeFilter filter) {
        return new JPAQuery<EmployeeEntity>(entityManager).select(employeeEntity).from(employeeEntity).where(employeeEntity.firstName.containsIgnoreCase(filter.getFirstName())).fetch();
    }
}
