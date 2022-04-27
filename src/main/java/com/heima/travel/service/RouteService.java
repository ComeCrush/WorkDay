package com.heima.travel.service;

import com.heima.travel.vo.ResultInfo;

/**
 * @author laofang
 * @description
 * @date 2021-06-04
 */
public interface RouteService {
    ResultInfo getRouteCareChoose();

    ResultInfo findRouteList(Integer cid, Integer curPage, String rname);

    ResultInfo findRouteByRid(Integer rid);

    ResultInfo findRoutesFavoriteRank(Integer curPage, String rname, Integer startPrice, Integer endPrice);
}
