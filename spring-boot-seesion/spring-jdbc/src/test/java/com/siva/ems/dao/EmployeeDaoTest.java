package com.siva.ems.dao;

import com.siva.ems.domain.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
 class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Test
     void addEmployeeTest() {
        Employee employee = new Employee();
        employee.setDeptNo(20);
        employee.setEmpName("Siva");
        employee.setEmpSalary(1000);
        Employee resEmp = employeeDao.addEmployee(employee);
        Assertions.assertEquals("Siva", resEmp.getEmpName());
    }
}
