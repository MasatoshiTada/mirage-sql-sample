package com.example.service;

import com.example.config.AppConfig;
import com.example.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

@ContextConfiguration(classes = AppConfig.class)
@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @Test
    @Transactional
    public void test_findByName_田() {
        List<Employee> list = employeeService.findByName("田");
        list.forEach(emp -> System.out.println(emp));
        assertThat(list.size(), is(4));
    }
}
