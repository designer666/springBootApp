package com.mine.springBootApp.repository;

import com.mine.springBootApp.dto.EmployeeFilter;
import com.mine.springBootApp.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeCustomRepository {

//    List<EmployeeEntity> findCustomQuery();

    List<EmployeeEntity> findByFilter(EmployeeFilter filter);
}
