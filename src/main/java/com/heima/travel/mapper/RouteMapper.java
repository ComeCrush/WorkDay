package com.heima.travel.mapper;

import com.heima.travel.pojo.Route;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteMapper {
    List<Route> getPopularRoutes();

    List<Route> getNewRoutes();

    List<Route> getThemeRoutes();

    List<Route> findRoutesByCategoryIdAndRouteName(@Param("cid") Integer cid, @Param("rname") String rname);

    Route findRouteInfoByRid(Integer rid);

    void updateRouteFavoriteCount(@Param("routId") Integer rid, @Param("incNum") int count);

    List<Route> findRoutesFavoriteRank(@Param("rname") String rname,
                                       @Param("startPrice") Integer startPrice,
                                       @Param("endPrice") Integer endPrice);
}
