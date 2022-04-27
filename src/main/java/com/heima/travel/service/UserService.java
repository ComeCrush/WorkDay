package com.heima.travel.service;

import com.heima.travel.pojo.User;
import com.heima.travel.vo.ResultInfo;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    List<User> findAll();

    ResultInfo register(User user, Object trueCode, String check);

    boolean activeUser(String code);

    User login(String userName, String password);

    ResultInfo getLoginUserData(HttpSession session);

    void loginOut(HttpSession session);
}
