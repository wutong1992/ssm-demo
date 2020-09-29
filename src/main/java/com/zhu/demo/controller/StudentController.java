package com.zhu.demo.controller;

import com.zhu.demo.common.BaseController;
import com.zhu.demo.common.Result;
import com.zhu.demo.entity.MyLog;
import com.zhu.demo.entity.StudentEntity;
import com.zhu.demo.entity.UserEntity;
import com.zhu.demo.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author: zhutao
 * @Date: 2020/4/29 16:03
 */
@RestController
@RequestMapping("/student")
@Api(tags = "学生信息记录")
public class StudentController extends BaseController {

    @Resource
    private StudentService studentService;

    @MyLog(value = "测试")
    @ApiOperation(value = "测试接口",notes = "仅输出提示语",httpMethod = "GET")
    @GetMapping("/test")
    public String hello(@Valid UserEntity userEntity) {
        return "Hello World!";
    }

    @ApiOperation(value = "查询所有学生记录",notes = "含分页",httpMethod = "GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "当前页码", paramType = "query", dataType = "long", example = "1"),
        @ApiImplicitParam(name = "limit", value = "每页大小", paramType = "query", dataType = "long", example = "2L")
    })
    @GetMapping("/all")
    public Result<List<StudentEntity>> queryList(@RequestParam("page") long page,@RequestParam("limit") long limit) {
        Result<List<StudentEntity>> success = Result.success(studentService.queryAllByPage(page, limit));
        return success;
    }

    @RequestMapping(value = "/demo", method = RequestMethod.POST)
    public String test(@RequestParam int param, @RequestParam String p) {
        System.out.println("请求参数是:" + param +">>>"+p);
        return "test is pass!";
    }
}
