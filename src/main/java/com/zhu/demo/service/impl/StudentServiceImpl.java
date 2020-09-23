package com.zhu.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhu.demo.dao.StudentMapper;
import com.zhu.demo.entity.StudentEntity;
import com.zhu.demo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhutao
 * @Date: 2020/4/29 10:10
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentEntity> implements StudentService {
    @Override
    public List<StudentEntity> queryAllByPage(long current, long size) {
        Page<StudentEntity> page = new Page();
        page.setCurrent(current);
        page.setSize(size);
        List<StudentEntity> records = this.baseMapper.queryAll(page).getRecords();
        return records;
    }
}
