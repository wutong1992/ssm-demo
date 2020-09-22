package com.zhu.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhu.demo.dao.SysLogMapper;
import com.zhu.demo.entity.SysLog;
import com.zhu.demo.service.SysLogService;
import org.springframework.stereotype.Service;

/**
 * @Author: zhutao
 * @Date: 2020/9/22 16:30
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {
}
