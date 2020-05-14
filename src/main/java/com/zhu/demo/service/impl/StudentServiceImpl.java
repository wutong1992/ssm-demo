package com.zhu.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhu.demo.dao.StudentMapper;
import com.zhu.demo.entity.StudentEntity;
import com.zhu.demo.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * @Author: zhutao
 * @Date: 2020/4/29 10:10
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentEntity> implements StudentService {
}
