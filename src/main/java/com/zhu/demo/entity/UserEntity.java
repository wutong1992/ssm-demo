package com.zhu.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户信息
 * @Author: zhutao
 * @Date: 2020/5/14 21:43
 */
@Data
@TableName(value = "user")
public class UserEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String password;
    private String perms;
}
