package com.example.service;

import com.example.entity.Employee;
import com.example.param.EmployeeParam;
import com.miragesql.miragesql.ClasspathSqlResource;
import com.miragesql.miragesql.SqlManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    private final SqlManager sqlManager;

    public EmployeeService(SqlManager sqlManager) {
        this.sqlManager = sqlManager;
    }

    @Transactional
    public List<Employee> findByName(String name) {
        EmployeeParam param = new EmployeeParam();
        param.setName("%" + name + "%");
        List<Employee> list = sqlManager.getResultList(Employee.class,
                new ClasspathSqlResource("META-INF/Employee/findByName.sql"), param);
        return list;
    }
}
