package com.zhu.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author: zhutao
 * @Date: 2020/4/29 9:37
 */
@Data
@TableName("student")
public class StudentEntity {
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String sex;
    private Date birthday;
}
