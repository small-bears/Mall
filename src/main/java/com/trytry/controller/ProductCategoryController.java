package com.trytry.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trytry.common.lang.Result;
import com.trytry.domain.ProductCategory;
import com.trytry.mapper.ProductCategoryMapper;
import com.trytry.service.ProductCategorySerivce;
import com.trytry.vo.ProductCategoryVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 分类信息表 前端控制器
 * </p>
 *
 * @author 我自己
 * @since 2021-11-15
 */
@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController {
    @Resource
    ProductCategorySerivce productCategorySerivce;

    @Resource
    ProductCategoryMapper productCategoryMapper;

    @GetMapping("/list")
    public Result<?> list(){
        List<ProductCategoryVO> all = productCategorySerivce.getAllProductCategoryVO();
        return Result.success(all);
    }
    @GetMapping("/{id}")
    public Result<?> findId(@PathVariable Integer id){
        ProductCategory byId = productCategorySerivce.getById(id);
        return Result.success(byId);
    }
    @GetMapping("/parent/{id}")
    public Result findPId(@PathVariable Integer id){
        List<ProductCategory> list = productCategorySerivce.list(Wrappers.<ProductCategory>lambdaQuery().eq(ProductCategory::getParentId, id));
        return Result.success(list);
    }
    @PostMapping
    public Result<?> save(@RequestBody ProductCategory productCategory){
        productCategory.setCreateTime(new Date());
        productCategory.setUpdateTime(new Date());
        productCategorySerivce.save(productCategory);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody ProductCategory productCategory){
        productCategorySerivce.updateById(productCategory);
        return Result.success();
    }
    @DeleteMapping("/{categoryId}")
    public Result<?> delete(@PathVariable Integer categoryId){
        productCategorySerivce.removeById(categoryId);
        return Result.success();

    }
    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids){
        productCategorySerivce.removeByIds(ids);
        return Result.success();
    }

    @GetMapping("/findAll")
    public Result findAll(){
        List<ProductCategory> list = productCategoryMapper.selectList(null);
        return Result.success(list);
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        new Page<>(pageNum,pageSize);
        Page<ProductCategory> page = productCategoryMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<ProductCategory>lambdaQuery().like(ProductCategory::getCategoryName, search));
        LambdaQueryWrapper<ProductCategory> query = Wrappers.<ProductCategory>lambdaQuery().orderByDesc(ProductCategory::getCategoryId);
        if (StrUtil.isNotBlank(search)) {
            query.like(ProductCategory::getCategoryName, search);
        }
        return Result.success(page);
    }

}

