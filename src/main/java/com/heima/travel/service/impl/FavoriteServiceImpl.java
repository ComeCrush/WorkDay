package com.heima.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heima.travel.mapper.FavoriteMapper;
import com.heima.travel.mapper.RouteMapper;
import com.heima.travel.pojo.Favorite;
import com.heima.travel.pojo.User;
import com.heima.travel.service.FavoriteService;
import com.heima.travel.vo.PageBean;
import com.heima.travel.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author laofang
 * @description
 * @date 2021-06-04
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteMapper favoriteMapper;

    @Autowired
    private RouteMapper routeMapper;

    @Override
    public Boolean isFavoriteByRid(Integer rid, Integer uid) {

            Favorite favorite= favoriteMapper.findFavorityByRouteIdAndUserId(rid,uid);
            if(favorite!=null){
               return true;
            }
        return false;
    }

    @Transactional
    @Override
    public ResultInfo addFavorite(Integer rid, int uid) {
        //添加收藏
        this.favoriteMapper.addFavorite(rid,uid);
        //跟新路线收藏量
        this.routeMapper.updateRouteFavoriteCount(rid,1);
        //获取最新收藏量
        int favoriteCount = this.routeMapper.findRouteInfoByRid(rid).getCount();

        return new ResultInfo(true,favoriteCount,null);
    }

    @Override
    public ResultInfo findFavoriteByPage(Integer curPage, HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");
        if(loginUser==null){
            return new ResultInfo(false,"用户未登录");
        }
       //分页
        PageHelper.startPage(curPage,8);
       List<Favorite> favoriteList=  this.favoriteMapper.findFavoriteByPage(loginUser.getUid());
        if (CollectionUtils.isEmpty(favoriteList)) {
            return new ResultInfo(true,null,"用户未收藏");
        }
        PageInfo<Favorite> pageInfo = new PageInfo<>(favoriteList);
        PageBean<Favorite> pageBean = new PageBean<>();
        pageBean.setTotalPage(pageInfo.getPages());
        pageBean.setPrePage(curPage-1);
        pageBean.setNextPage(curPage+1);
        pageBean.setFirstPage(1);
        pageBean.setCount(pageInfo.getTotal());
        pageBean.setCurPage(curPage);
        pageBean.setPageSize(pageInfo.getPageSize());
        pageBean.setData(favoriteList);
        return new ResultInfo(true,pageBean,null);
    }
}
