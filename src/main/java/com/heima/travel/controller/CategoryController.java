package com.heima.travel.controller;

import com.heima.travel.service.CategoryService;
import com.heima.travel.vo.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.scene.shape.VLineTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laofang
 * @description
 * @date 2021-06-04
 */
@Api(value = "分类管理controller",tags = "分类管理controller2")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取所有分类信息",notes = "获取所有分类信息，方便web展示")
    @PostMapping("/findAllCategory")
    public ResultInfo findAllCategory(){
        ResultInfo result= this.categoryService.findAllCategory();
        return result;
    }
}
