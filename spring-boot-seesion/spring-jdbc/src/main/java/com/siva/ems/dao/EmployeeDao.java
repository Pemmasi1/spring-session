package com.siva.ems.dao;

import com.siva.ems.domain.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> getEmployees();
    Employee addEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    Boolean deleteEmployee(int empNo);
    Employee findById(int empNo);
    List<Employee> search(String str);
    List<Employee> findByDeptNo(int deptNo);

    List<Employee> addEmployees(List<Employee> employees);

}
