package com.zhu.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhu.demo.entity.StudentEntity;

/**
 * @Author: zhutao
 * @Date: 2020/4/29 9:39
 */
public interface StudentMapper extends BaseMapper<StudentEntity> {

    /**
     * 分页查询接口
     * @param page
     * @return
     */
    IPage<StudentEntity> queryAll(Page page);
}
