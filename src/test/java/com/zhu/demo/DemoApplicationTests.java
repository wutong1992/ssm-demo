package com.zhu.demo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhu.demo.dao.StudentMapper;
import com.zhu.demo.entity.StudentEntity;
import com.zhu.demo.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private StudentService studentService;

    @Resource
    private StudentMapper studentMapper;

    @Test
    void contextLoads() {
        System.out.println("hello");
    }

    @Test
    void testCollect() {
        List<StudentEntity> studentEntities = studentService.list();
        System.out.println(studentEntities.size());
        studentEntities.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        StudentEntity student = new StudentEntity();
        student.setName("zhutao");
        student.setSex("男");
        student.setAge(22);
        student.setBirthday(new Date());
        studentService.save(student);
    }

    @Test
    void testDelete() {
        studentService.removeById(5);
    }

    @Test
    void pageList() {
        Page<StudentEntity> page = new Page();
        page.setCurrent(0);
        page.setSize(2L);
        System.out.println(studentMapper.queryAll(page).getRecords());
    }

}
