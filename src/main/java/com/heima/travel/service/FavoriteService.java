package com.heima.travel.service;

import com.heima.travel.vo.ResultInfo;

import javax.servlet.http.HttpSession;

/**
 * @author laofang
 * @description
 * @date 2021-06-04
 */
public interface FavoriteService {
    Boolean isFavoriteByRid(Integer rid, Integer uId);

    ResultInfo addFavorite(Integer rid, int uid);

    ResultInfo findFavoriteByPage(Integer curPage, HttpSession session);
}
