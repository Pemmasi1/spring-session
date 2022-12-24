package com.siva.ems.dao;

import com.siva.ems.dao.mapper.EmployeeRowMapper;
import com.siva.ems.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@Slf4j
public class EmployeeDaoImpl implements EmployeeDao {

    private static final String ADD_EMP = "insert into employee(emp_name,emp_salary,dept_no) values(?,?,?)";
    private static final String GET_EMPLOYEES = "select emp_no,emp_name,emp_salary,dept_no from employee";

    private static final String GET_EMP_BY_ID = "select emp_no,emp_name,emp_salary,dept_no from employee where emp_no=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> getEmployees() {
        List<Employee> list = jdbcTemplate.query(GET_EMPLOYEES, new EmployeeRowMapper());
        log.info("Employee count :{}", list.size());
        return list;
    }

    @Override
    @Transactional
    public Employee addEmployee(Employee employee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(ADD_EMP, new String[]{"emp_no"});
            ps.setString(1, employee.getEmpName());
            ps.setFloat(2, employee.getEmpSalary());
            ps.setLong(3, employee.getDeptNo());
            return ps;
        }, keyHolder);
        int empno =  keyHolder.getKey().intValue()  ;
        employee.setEmpNo(empno);
        log.info("Employee {} is added with id {}", employee.getEmpName(), employee.getEmpNo());
        return employee;
    }

    @Override
    public Employee findById(int empno) {
        try {
            return jdbcTemplate.query(con -> {
                PreparedStatement ps = con.prepareStatement(GET_EMP_BY_ID);
                ps.setInt(1, empno);
                return ps;
            }, new EmployeeRowMapper()).get(0);
        } catch (IndexOutOfBoundsException exp) {
            throw new RuntimeException(String.format("Employee %s is not present in DB", empno));
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement("update employee set emp_name=?, emp_salary=?,dept_no = ? where emp_no=?");
            ps.setString(1, employee.getEmpName());
            ps.setFloat(2, employee.getEmpSalary());
            ps.setLong(3, employee.getDeptNo());
            ps.setInt(4,employee.getEmpNo());
            return ps;
        });
        return employee;
    }

    @Override
    public Boolean deleteEmployee(int empno) {
        int count = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement("delete from employee where emp_no=?");
            ps.setInt(1,empno);
            return ps;
        });
        return count!=0;
    }


    @Override
    public List<Employee> search(String str) {
        return  jdbcTemplate.query(con -> {
            PreparedStatement ps = con.prepareStatement("select emp_no,emp_name,emp_salary,dept_no from employee where lower(emp_name) like ?");
            ps.setString(1,"%"+str.toLowerCase()+"%");
            return ps;
        },new EmployeeRowMapper());
    }


    @Override
    public List<Employee> findByDeptNo(int deptno) {
        return  jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement("select emp_no,emp_name,emp_salary,dept_no from employee where dept_no=?");
                ps.setInt(1,deptno);
                return ps;
            }
        },new EmployeeRowMapper());
    }

    @Override
    @Transactional
    public List<Employee> addEmployees(List<Employee> list) {
        jdbcTemplate.batchUpdate(ADD_EMP, list, 50, (ps, employee) -> {
            ps.setString(1, employee.getEmpName());
            ps.setFloat(2, employee.getEmpSalary());
            ps.setLong(3, employee.getDeptNo());
        });
        return list;
    }
}
