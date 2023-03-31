package com.trytry.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trytry.common.lang.Result;
import com.trytry.domain.CustomerInfo;
import com.trytry.domain.Info;
import com.trytry.domain.Master;
import com.trytry.domain.Shopping;
import com.trytry.mapper.InfoMapper;
import com.trytry.mapper.MasterMapper;
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
@RequestMapping("/master")
public class MasterController {

    @Resource
    private MasterMapper masterMapper;
    @Resource
    private InfoMapper infoMapper;

    @GetMapping("/findAll")
    public List<Master> findAll(){
        List<Master> masters = masterMapper.selectList(null);
        return masters;
    }
    @GetMapping("/finds/{customerId}")
    public Result<?> findCustomerId(@RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                    @PathVariable Integer customerId){
        Page<Master> page = masterMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Master>lambdaQuery().eq(Master::getCustomerId, customerId));
        return Result.success(page);
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id){
        masterMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/find/{id}")
    public Master find(@PathVariable("id") Integer id){
        return this.masterMapper.selectById(id);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody Master master){
        masterMapper.updateById(master);
        return Result.success();
    }

    @PostMapping("/add")
    public Result<?> insert(@RequestBody Master master){
        masterMapper.insert(master);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids){
        masterMapper.deleteBatchIds(ids);
        return Result.success();
    }
    @GetMapping("/count")
    public Result Count(){
        Integer count = masterMapper.selectCount(null);
        return Result.success(count);
    }
    @GetMapping("/buy/{productId}")
    public Result buy(@PathVariable Integer productId){
        Info info = infoMapper.selectById(productId);
        String orderSn = IdUtil.getSnowflake().nextIdStr();
        String payUrl = "http://localhost:8888/alipay/pay?subject=" + info.getProductName() + "&traceNo=" + orderSn + "&totalAmount=" + info.getProductPrice();
        return Result.success(payUrl);
    }


    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        new Page<>(pageNum, pageSize);
        Page<Master> masterPage = masterMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Master>lambdaQuery().like(Master::getProductName, search));
        LambdaQueryWrapper<Master> query = Wrappers.<Master>lambdaQuery().orderByDesc(Master::getOrderId);
        if (StrUtil.isNotBlank(search)) {
            query.like(Master::getProductName, search);
        }
        return Result.success(masterPage);
    }

}

