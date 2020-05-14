package com.zhu.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhu.demo.dao.UserMapper;
import com.zhu.demo.entity.UserEntity;
import com.zhu.demo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author: zhutao
 * @Date: 2020/5/14 21:50
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
}
