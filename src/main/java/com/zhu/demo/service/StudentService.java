package com.zhu.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhu.demo.entity.StudentEntity;

import java.util.List;

/**
 * @Author: zhutao
 * @Date: 2020/4/29 10:09
 */
public interface StudentService extends IService<StudentEntity> {
    /**
     * 分页查询学生信息
     * @param current 当前页码
     * @param size 每页数目
     * @return
     */
    List<StudentEntity> queryAllByPage(long current, long size);
}
