package com.siva.ems.web;

import com.siva.ems.dao.DepartmentDao;
import com.siva.ems.dao.EmployeeDao;
import com.siva.ems.domain.Department;
import com.siva.ems.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @PostMapping("/addEmployee")
    public ResponseEntity addNewEmploy(@RequestBody Employee employee) {
        employeeDao.addEmployee(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getDepartments")
    public List<Department> getDepartments() {
        return departmentDao.getDepartments();
    }

    @GetMapping("/getEmployees")
    public List<Employee> getAllEmployees() {
        return employeeDao.getEmployees();
    }
    @PostMapping("/addEmployees")
    public ResponseEntity addEmployees(@RequestBody List<Employee> employee) {
        employeeDao.addEmployees(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getEmployeesByDept")
    public List<Employee> employeesByDept(@RequestParam int deptNo) {
        return employeeDao.findByDeptNo(deptNo);
    }



//    public String welcome() {
//        return "Hello World";
//    }

}
