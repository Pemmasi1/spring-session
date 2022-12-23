package com.siva.ems.dao.mapper;

import com.siva.ems.domain.Department;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentRowMapper implements RowMapper<Department> {
    @Override
    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {

       Department department = new Department();
       department.setDeptNo(rs.getInt("dept_no"));
       department.setDName(rs.getString("d_name"));
       department.setLocation(rs.getString("location"));
       return department;
    }
}
