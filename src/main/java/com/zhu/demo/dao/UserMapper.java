package com.zhu.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhu.demo.entity.UserEntity;

/**
 * @Author: zhutao
 * @Date: 2020/5/14 21:47
 */
public interface UserMapper extends BaseMapper<UserEntity> {
    /**
     * 分页查询接口
     * @param page
     * @return
     */
    IPage<UserEntity> queryAll(Page page);
}
