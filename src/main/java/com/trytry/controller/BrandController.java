package com.trytry.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.trytry.common.lang.Result;
import com.trytry.domain.Brand;
import com.trytry.service.BrandSerivce;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 我自己
 * @since 2021-11-19
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Resource
    BrandSerivce brandSerivce;

    @GetMapping("/find")
    public Result find(){
        List<Brand> list = brandSerivce.list();
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result findBId(@PathVariable Integer id){
        List<Brand> list = brandSerivce.list(Wrappers.<Brand>lambdaQuery().eq(Brand::getOneCategoryId,id).or().eq(Brand::getTwoCategoryId,id));
        return Result.success(list);
    }

}

