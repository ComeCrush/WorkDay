package com.heima.travel.controller;

import com.heima.travel.service.RouteService;
import com.heima.travel.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laofang
 * @description
 * @date 2021-06-04
 */
@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping("/routeCareChoose")
    public ResultInfo getRouteCareChoose(){
        ResultInfo resultInfo= this.routeService.getRouteCareChoose();
        return resultInfo;
    }

    @PostMapping("/findRouteList")
    public ResultInfo findRouteList(
            @RequestParam(value = "cid",required = false) Integer cid,
            @RequestParam(value = "curPage",required = false,defaultValue = "1") Integer curPage,
            @RequestParam(value = "rname",required = false) String rname){
        ResultInfo result= this.routeService.findRouteList(cid,curPage,rname);
        return result;
    }

    @PostMapping("/findRouteByRid")
    public ResultInfo findRouteByRid(@RequestParam("rid") Integer rid){
       ResultInfo info= this.routeService.findRouteByRid(rid);
       return info;
    }
    @PostMapping("/findRoutesFavoriteRank")
    public ResultInfo findRoutesFavoriteRank(@RequestParam(value = "curPage",required = false,defaultValue = "1") Integer curPage,
                                             @RequestParam(value = "rname",required = false) String rname,
                                             @RequestParam(value = "startPrice",required = false) Integer startPrice,
                                             @RequestParam(value = "endPrice",required = false) Integer endPrice){
        ResultInfo info= this.routeService.findRoutesFavoriteRank(curPage,rname,startPrice,endPrice);
        return info;
    }

}
