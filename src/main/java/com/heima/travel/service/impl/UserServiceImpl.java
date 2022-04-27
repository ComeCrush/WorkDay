package com.heima.travel.service.impl;

import com.heima.travel.mapper.UserMapper;
import com.heima.travel.pojo.User;
import com.heima.travel.service.UserService;
import com.heima.travel.util.MailUtil;
import com.heima.travel.util.Md5Util;
import com.heima.travel.util.UuidUtil;
import com.heima.travel.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author laofang
 * @description
 * @date 2021-06-04
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> findAll() {
        System.out.println("findAll run");
        return userMapper.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultInfo register(User user, Object trueCode, String check) {
        ResultInfo result=null;
        if (check==null|| !check.equals(trueCode)) {
            return new ResultInfo(false,null,"验证码输入错误");
        }else if(user==null|| StringUtils.isEmpty(user.getName())|| StringUtils.isEmpty(user.getPassword())){
            throw new RuntimeException("信息填写有误|用户名或者密码不能为空");
        }
        //判断用户名是否注册
       User dbUser=  userMapper.getUserByUserName(user.getName());
        if (dbUser!=null) {
            throw new RuntimeException("用户名已存在");
        }
        //封装业务状态，激活状态为未激活
        user.setStatus("N");
        //封装业务装填激活码
        user.setCode(UuidUtil.getUuid());
        //将明文密码md5加密
        user.setPassword(Md5Util.encodeByMd5(user.getPassword()));
        //将用户信息添加到数据库
        userMapper.addUser(user);
        //发送邮箱
        String content="<a href='http://localhost:8080/user/active?code=" + user.getCode() + "'>用户激活</a>";
        MailUtil.sendMail(user.getEmail(),content);
        result=new ResultInfo(true,null,null);
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean activeUser(String code) {
       int count= this.userMapper.activeUser(code);
        return count>0;
    }

    @Override
    public User login(String userName, String password) {
        if(StringUtils.isEmpty(userName)|| StringUtils.isEmpty(password)){
            throw new RuntimeException("用户名或者密码错误");
        }
        //密码转换为加密
        password=Md5Util.encodeByMd5(password);
       User user=this.userMapper.findUserByUserNameAndPassword(userName,password);
       return user;
    }

    @Override
    public ResultInfo getLoginUserData(HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if(loginUser!=null){
            return new ResultInfo(true,loginUser,null);
        }
        return new ResultInfo(false,null,null);
    }

    @Override
    public void loginOut(HttpSession session) {
        session.removeAttribute("loginUser");
    }
}
