package com.mine.springBootApp.projection;

import org.springframework.beans.factory.annotation.Value;

public interface  EmployeeNameView {

    @Value("#{target.id + ' ' + target.firstName}") //Можно менять то, что возвращают методы
    String getFirstName();

    String getSecondName();
}
