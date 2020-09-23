package com.zhu.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    @NotEmpty(message = "传入的是空字符串，请传值")
    @NotNull(message = "传入的是空值，请传值")
    private String name;
    private String password;
    private String perms;
}
