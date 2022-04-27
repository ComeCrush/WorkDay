package com.heima.travel.controller;

import com.heima.travel.pojo.User;
import com.heima.travel.service.FavoriteService;
import com.heima.travel.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author laofang
 * @description
 * @date 2021-06-04
 */
@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/isFavoriteByRid")
    public ResultInfo isFavoriteByRid(@RequestParam("rid") Integer rid, HttpSession session){
        //获取用户信息
        User loginUser = (User) session.getAttribute("loginUser");
        if(loginUser==null){
            return new ResultInfo(false,"用户未登录",null);
        }
        Boolean isFavorited= favoriteService.isFavoriteByRid(rid,loginUser.getUid());
        if (isFavorited) {
            return new ResultInfo(true,true,null);
        }else{
            return new ResultInfo(true,false,"用户为收藏");
        }
    }

    @PostMapping("/addFavorite")
    public ResultInfo addFavorite(@RequestParam("rid") Integer rid,
                                  HttpSession session){
        User loginUser = (User) session.getAttribute("loginUser");
        if(loginUser==null){
            return new ResultInfo(false,0,"用户未登录");
        }
        return this.favoriteService.addFavorite(rid,loginUser.getUid());
    }

    @PostMapping("/findFavoriteByPage")
    public ResultInfo findFavoriteByPage(@RequestParam(value = "curPage",required = false,defaultValue = "1") Integer curPage,
                                         HttpSession session){
       ResultInfo info= this.favoriteService.findFavoriteByPage(curPage,session);
        return info;
    }
}
