package com.zhu.demo.controller;

import com.zhu.demo.common.BaseController;
import com.zhu.demo.common.Result;
import com.zhu.demo.entity.MyLog;
import com.zhu.demo.entity.StudentEntity;
import com.zhu.demo.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: zhutao
 * @Date: 2020/4/29 16:03
 */
@RestController
@RequestMapping("/student")
@Api(tags = "学生信息记录")
public class StudentController extends BaseController {

    @Autowired
    private StudentService studentService;

    @MyLog(value = "测试")
    @ApiOperation(value = "测试接口",notes = "仅输出提示语",httpMethod = "GET")
    @GetMapping("/test")
    public String hello() {
        return "Hello World!";
    }

    @ApiOperation(value = "查询所有学生记录",notes = "不含分页",httpMethod = "GET")
    /**
     @ApiImplicitParam(dataType = "string",name = "name",value = "姓名",required = true)
     @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "当前页码", paramType = "query", dataType = "int", example = "1"),
        @ApiImplicitParam(name = "limit", value = "每页大小", paramType = "query", dataType = "int", example = "10")
     }) **/
    @GetMapping("/all")
    public Result<List<StudentEntity>> queryList() {
        Result<List<StudentEntity>> success = Result.success(studentService.list());
        return success;
    }
}
