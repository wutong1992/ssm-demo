package com.zhu.demo.controller;

import com.zhu.demo.common.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: zhutao
 * @Date: 2020/4/29 17:20
 */
@Controller
public class UserController extends BaseController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequiresPermissions("user:add")
    @RequestMapping("/add")
    public String add(){
        return "add";
    }

    @RequiresPermissions("user:update")
    @RequestMapping("/update")
    public String update(){
        return "update";
    }

    /**
     * 登录逻辑处理
     * @param username
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("/checkLogin")
    public String checkLogin(String username, String password, Model model){
        logger.info("username = " + username);
        // Shiro验证
        // 1、获取Subject
        Subject subject = SecurityUtils.getSubject();
        // 2、封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        // 3、执行登录方法
        try{
            subject.login(token);
            // 登录成功，跳转index.html
            return "redirect:/index";
        } catch (UnknownAccountException e){
            // 登录失败 用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            // 登录失败 密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }

    /**
     * 登出系统
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "login";
    }
}
