package com.trytry.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.trytry.common.lang.Result;
import com.trytry.domain.Comment;
import com.trytry.domain.Shopping;
import com.trytry.mapper.ShoppingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 111
 * @since 2021-06-30
 */
@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    @Resource
    private ShoppingMapper shoppingMapper;

    @GetMapping("/findAll")
    public List<Shopping> findAll(){
        List<Shopping> shops = shoppingMapper.selectList(null);
        return shops;
    }
    @GetMapping("/finds/{customerId}")
    public Result<?> findCustomerId(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "5") Integer pageSize,
                                   @PathVariable Integer customerId){
        Page<Shopping> page = shoppingMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Shopping>lambdaQuery().eq(Shopping::getCustomerId, customerId));
        return Result.success(page);
    }
    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id){
        shoppingMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/find/{id}")
    public Shopping find(@PathVariable("id") Integer id){
        return this.shoppingMapper.selectById(id);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody Shopping shopping){
        shoppingMapper.updateById(shopping);
        return Result.success();
    }

    @PostMapping("/add")
    public Result<?> insert(@RequestBody Shopping shopping){
        shoppingMapper.insert(shopping);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids){
        shoppingMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        new Page<>(pageNum, pageSize);
        Page<Shopping> shoppingPage = shoppingMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Shopping>lambdaQuery().like(Shopping::getProductName, search));
        LambdaQueryWrapper<Shopping> query = Wrappers.<Shopping>lambdaQuery().orderByDesc(Shopping::getProductId);
        if (StrUtil.isNotBlank(search)) {
            query.like(Shopping::getProductName, search);
        }
        return Result.success(shoppingPage);
    }

}

