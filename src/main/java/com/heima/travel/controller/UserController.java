package com.heima.travel.controller;

import com.heima.travel.pojo.User;
import com.heima.travel.service.UserService;
import com.heima.travel.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author laofang
 * @description
 * @date 2021-06-04
 */
//@Controller
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
//    @ResponseBody
    public List<User> findAll(){
        List<User> all = userService.findAll();
        return all;
    }


    @RequestMapping("/register")
//    @ResponseBody
    public ResultInfo register(User user, String check, HttpSession session){
        Object trueCode = session.getAttribute("CHECKCODE_SERVER");
        ResultInfo  result= this.userService.register(user,trueCode,check);
        return result;
    }

    @RequestMapping("/active")
    public void activeUser(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        boolean result= this.userService.activeUser(code);
        if(result){
            response.sendRedirect("/login.html");
        }else{
            throw new RuntimeException("激活失败");
        }
    }

    @PostMapping("/login")
//    @ResponseBody
    public ResultInfo login(@RequestParam("username") String userName,
                            @RequestParam("password") String password, HttpSession session){

        User dbUser=  this.userService.login(userName,password);
       if(dbUser!=null){
           session.setAttribute("loginUser",dbUser);
           return new ResultInfo(true,null,null);
       }else{
           return new ResultInfo(false,null,"用户名或者密码错误");
       }
    }

    @PostMapping("/getLoginUserData")
    //@ResponseBody
    public ResultInfo getLoginUserData(HttpSession session){
        return this.userService.getLoginUserData(session);
    }

    @GetMapping("/loginOut")
    public void loginOut(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        this.userService.loginOut(session);
        response.sendRedirect("/login.html");
    }
}
