package com.siva.ems.dao;


import com.siva.ems.domain.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
 class DepartmentDaoTest {

    @Autowired
    private DepartmentDao departmentDao;

    @Test
    void getDepartmentTest() {
        List<Department> list = departmentDao.getDepartments();
        Assertions.assertEquals(4, list.size());
    }


}
