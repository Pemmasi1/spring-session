package com.siva.ems.dao;

import com.siva.ems.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.LambdaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@Slf4j
public class EmployeeDaoImpl implements EmployeeDao{
    private static final String ADD_EMP = "insert into employee(emp_name,emp_salary,dept_no) values(?,?,?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> getEmployees() {
        return new ArrayList<>();
    }

    @Override
    public Employee addEmployee(Employee employee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(ADD_EMP, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getEmpName());
            ps.setFloat(2, employee.getEmpSalary());
            ps.setInt(3,employee.getDeptNo());
            return ps;
        }, keyHolder);
        int empno = Objects.nonNull(keyHolder.getKey())?keyHolder.getKey().intValue():0;
        employee.setEmpNo(empno);
        log.info("Employee {} is added with id {}", employee.getEmpName(), employee.getEmpNo());
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return null;
    }

    @Override
    public Boolean deleteEmployee(int empNo) {
        return true;
    }

    @Override
    public Employee findById(int empNo) {
        return new Employee();
    }

    @Override
    public List<Employee> search(String str) {
        return new ArrayList<>();
    }

    @Override
    public List<Employee> findByDeptNo(int deptNo) {
        return new ArrayList<>();
    }

    @Override
    public List<Employee> addEmployees(List<Employee> employees) {
        return new ArrayList<>();
    }
}
