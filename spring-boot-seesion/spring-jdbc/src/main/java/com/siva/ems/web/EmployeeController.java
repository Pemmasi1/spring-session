package com.siva.ems.web;

import com.siva.ems.dao.DepartmentDao;
import com.siva.ems.dao.EmployeeDao;
import com.siva.ems.domain.Department;
import com.siva.ems.domain.Employee;
import com.siva.ems.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;


import java.util.List;

@RestController
@RequestMapping("/")
@Api(value = "EmployeeResource")
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentDao departmentDao;

    @PostMapping("/addEmployee")
    public ResponseEntity addNewEmploy(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getDepartments")
    public List<Department> getDepartments() {
        return departmentDao.getDepartments();
    }

    @GetMapping("/getEmployees")
    @ApiOperation("This Api returns the list of employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getEmployees();
    }
    @PostMapping("/addEmployees")
    public ResponseEntity addEmployees(@RequestBody List<Employee> employee) {
        employeeService.addEmployees(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getEmployeesByDept")
    public List<Employee> employeesByDept(@RequestParam int deptNo) {
        return employeeDao.findByDeptNo(deptNo);
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updateEmployee(employee));
    }

    @DeleteMapping("/{empno}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("emp_no") int empno) {
        boolean isDeleted = employeeService.deleteEmployee(empno);
        if (isDeleted) {
            return ResponseEntity.ok("Deletion of employee is successful");
        } else {
            return ResponseEntity.ok("Delete operation failed");
        }
    }
    @GetMapping("/{empno}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("empno")int empno) {
        return ResponseEntity.ok(employeeService.getEmployeeById(empno));
    }


    @GetMapping("/search/{str}")
    public ResponseEntity<List<Employee>> search(@PathVariable("str") String str) {
        return ResponseEntity.ok(employeeService.search(str));
    }

}
