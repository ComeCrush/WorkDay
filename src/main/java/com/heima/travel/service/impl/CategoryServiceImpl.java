package com.heima.travel.service.impl;

import com.heima.travel.mapper.CategoryMapper;
import com.heima.travel.pojo.Category;
import com.heima.travel.service.CategoryService;
import com.heima.travel.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author laofang
 * @description
 * @date 2021-06-04
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ResultInfo findAllCategory() {

        List<Category> list= this.categoryMapper.findAllCategory();

        return new ResultInfo(true,list,null);
    }
}
