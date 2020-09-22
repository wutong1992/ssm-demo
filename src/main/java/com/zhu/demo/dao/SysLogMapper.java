package com.zhu.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhu.demo.entity.SysLog;

/**
 * @Author: zhutao
 * @Date: 2020/9/22 16:26
 */
public interface SysLogMapper extends BaseMapper<SysLog> {
    /**
     * 分页查询接口
     * @param page
     * @return
     */
    IPage<SysLog> queryAll(Page page);
}
