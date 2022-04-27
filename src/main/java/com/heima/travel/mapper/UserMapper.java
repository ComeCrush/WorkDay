package com.heima.travel.mapper;

import com.heima.travel.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> findAll();

    User getUserByUserName(@Param("userName") String name);

    void addUser(User user);

    int activeUser(@Param("code") String code);

    User findUserByUserNameAndPassword(@Param("username") String userName, @Param("password") String password);
}
