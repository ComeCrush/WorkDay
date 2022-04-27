package com.heima.travel.mapper;

import com.heima.travel.pojo.Favorite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author laofang
 * @description
 * @date 2021-06-04
 */
public interface FavoriteMapper {
    Favorite findFavorityByRouteIdAndUserId(@Param("rid") Integer rid,
                                            @Param("uid") int uid);

    void addFavorite(@Param("rid") Integer rid, @Param("uid") int uid);

    List<Favorite> findFavoriteByPage(int uid);
}
