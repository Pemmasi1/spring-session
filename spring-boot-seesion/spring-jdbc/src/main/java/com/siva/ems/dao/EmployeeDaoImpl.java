package com.siva.ems.dao;

import com.siva.ems.dao.mapper.EmployeeRowMapper;
import com.siva.ems.domain.Employee;
import com.siva.ems.platform.BaseException;
import com.siva.ems.platform.LambdaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class EmployeeDaoImpl implements EmployeeDao{
    private static final String ADD_EMP = "insert into employee(emp_name,emp_salary,dept_no) values(?,?,?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Employee> getEmployees() {
        List<Employee> list = jdbcTemplate.query("select * from employee", new EmployeeRowMapper());
        log.info("Employee count is {}",list.size());
        return list;
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
        int empno = LambdaUtil.safeGet(keyHolder::getKey).get().intValue();
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
       List<Employee> list = jdbcTemplate.query(String.format("select * from employee where dept_no = %d", deptNo), new EmployeeRowMapper());
       if (list.isEmpty()) {
           throw new BaseException("Did not Fetch Results", 423);
       }
       return list;
    }

    @Override
    public List<Employee> addEmployees(List<Employee> employees) {
        employees.forEach(this::addEmployee);
        return employees;
    }
}
