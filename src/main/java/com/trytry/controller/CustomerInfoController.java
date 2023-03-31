package com.trytry.controller;


import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import com.trytry.common.lang.Result;
import com.trytry.domain.CustomerInfo;
import com.trytry.mapper.CustomerInfoMapper;
import com.trytry.service.CustomerInfoSerivce;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author 我自己
 * @since 2021-10-27
 */
@RestController
@RequestMapping("/customerInfo")
public class CustomerInfoController {

    @Resource
    CustomerInfoMapper customerInfoMapper;
    @Resource
    CustomerInfoSerivce customerInfoSerivce;

    @PostMapping
    public Result<?> save(@Validated @RequestBody CustomerInfo customerInfo){
        CustomerInfo res = customerInfoSerivce.getOne(Wrappers.<CustomerInfo>lambdaQuery().eq(CustomerInfo::getUsername, customerInfo.getUsername()));
        if (res != null){
            return Result.fail("用户名已存在");
        }
        customerInfoSerivce.save(customerInfo);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<?> update(@Validated @RequestBody CustomerInfo customerInfo){
        customerInfoSerivce.updateById(customerInfo);
        return Result.success();
    }
    @DeleteMapping("/{customerId}")
    public Result<?> delete(@PathVariable Integer customerId){
        customerInfoSerivce.removeById(customerId);
        return Result.success();
    }
    @GetMapping("/find/{id}")
    public CustomerInfo find(@PathVariable("id") Integer id){
        return this.customerInfoMapper.selectById(id);
    }
    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids){
         customerInfoSerivce.removeByIds(ids);
         return Result.success();
    }

    @GetMapping("/count")
    public Result Count(){
        Integer count = customerInfoMapper.selectCount(null);
        return Result.success(count);
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        new Page<>(pageNum,pageSize);
        Page<CustomerInfo> customerInfoPage = customerInfoMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<CustomerInfo>lambdaQuery().like(CustomerInfo::getUsername, search).or().like(CustomerInfo::getCustomerName,search));
        LambdaQueryWrapper<CustomerInfo> query = Wrappers.<CustomerInfo>lambdaQuery().orderByDesc(CustomerInfo::getCustomerId);
        if (StrUtil.isNotBlank(search)) {
            query.like(CustomerInfo::getUsername, search).or().like(CustomerInfo::getCustomerName,search);
        }
        return Result.success(customerInfoPage);
    }
}

