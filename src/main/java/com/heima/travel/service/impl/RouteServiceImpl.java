package com.heima.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heima.travel.mapper.RouteMapper;
import com.heima.travel.pojo.Route;
import com.heima.travel.service.RouteService;
import com.heima.travel.vo.PageBean;
import com.heima.travel.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @author laofang
 * @description
 * @date 2021-06-04
 */
@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteMapper routeMapper;

    @Override
    public ResultInfo getRouteCareChoose() {
        //查询人气路线
        PageHelper.startPage(0,4);
        List<Route> popuRoutes=  this.routeMapper.getPopularRoutes();
        //获取最新路线前4
        PageHelper.startPage(0,4);
        List<Route> newRoutes=  this.routeMapper.getNewRoutes();
        //获取主题
        PageHelper.startPage(0,4);
        List<Route> themeRoutes=  this.routeMapper.getThemeRoutes();
        //将数据组装到map集合
        HashMap<String, List> mapInfo = new HashMap<>();
        mapInfo.put("popularity",popuRoutes);
        mapInfo.put("news",newRoutes);
        mapInfo.put("themes",themeRoutes);
        return new ResultInfo(true,mapInfo,null);
    }

    @Override
    public ResultInfo findRouteList(Integer cid, Integer curPage, String rname) {
        PageHelper.startPage(curPage,8);
        List<Route> routes= this.routeMapper.findRoutesByCategoryIdAndRouteName(cid,rname);
        if (CollectionUtils.isEmpty(routes)) {
            return new ResultInfo(false,null,"暂无此分类路线信息");
        }
        PageInfo<Route> pageInfo = new PageInfo<>(routes);
        PageBean<Route> pageBean = new PageBean<>(routes,1,curPage-1,curPage,curPage+1,
                pageInfo.getPages(),pageInfo.getTotal(),8);
        return new ResultInfo(true,pageBean,null);
    }

    @Override
    public ResultInfo findRouteByRid(Integer rid) {
       Route route=this.routeMapper.findRouteInfoByRid(rid);
        if (route==null) {
            return new ResultInfo(false,"没有相关信息");
        }
        return new ResultInfo(true,route,null);
    }

    @Override
    public ResultInfo findRoutesFavoriteRank(Integer curPage, String rname, Integer startPrice, Integer endPrice) {
        //分页
        PageHelper.startPage(curPage,8);
        //根据分类名称和金额范围查询
        List<Route> routes= this.routeMapper.findRoutesFavoriteRank(rname,startPrice,endPrice);
        if (CollectionUtils.isEmpty(routes)) {
            throw new RuntimeException("查询数据异常");
        }
        PageInfo<Route> pageInfo = new PageInfo<>(routes);
        PageBean<Route> pageBean = new PageBean<>();
        pageBean.setData(routes);
        pageBean.setCurPage(curPage);
        pageBean.setCount(pageInfo.getTotal());
        pageBean.setFirstPage(1);
        pageBean.setNextPage(curPage+1);
        pageBean.setPrePage(curPage-1);
        pageBean.setTotalPage(pageInfo.getPages());
        return new ResultInfo(true,pageBean,null);
    }
}
