package com.trytry.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.trytry.common.lang.Result;
import com.trytry.domain.Receipt;
import com.trytry.domain.Shopping;
import com.trytry.mapper.ReceiptMapper;
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
@RequestMapping("/receipt")
public class ReceiptController {

    @Resource
    private  ReceiptMapper  receiptMapper;

    @GetMapping("/findAll")
    public List<Receipt> findAll(){
        List<Receipt>  receipts =  receiptMapper.selectList(null);
        return  receipts;
    }
    @GetMapping("/finds/{customerId}")
    public Result<?> findCustomerId(@RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                    @PathVariable Integer customerId){
        Page<Receipt> page = receiptMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Receipt>lambdaQuery().eq(Receipt::getCustomerId, customerId));
        return Result.success(page);
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id){
        receiptMapper.deleteById(id);
        return  Result.success();
    }

    @GetMapping("/find/{id}")
    public Receipt find(@PathVariable("id") Integer id){
        return this.receiptMapper.selectById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Receipt receipt){
        receiptMapper.updateById(receipt);
        return Result.success();
    }

    @PostMapping("/add")
    public Result insert(@RequestBody Receipt receipt){
        receiptMapper.insert(receipt);
        return Result.success();
    }
    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids){
        receiptMapper.deleteBatchIds(ids);
        return Result.success();
    }
    @GetMapping("/count")
    public Result Count(){
        Integer count = receiptMapper.selectCount(null);
        return Result.success(count);
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        new Page<>(pageNum, pageSize);
        Page<Receipt> receiptPage = receiptMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Receipt>lambdaQuery().like(Receipt::getProductName, search));
        LambdaQueryWrapper<Receipt> query = Wrappers.<Receipt>lambdaQuery().orderByDesc(Receipt::getProductId);
        if (StrUtil.isNotBlank(search)) {
            query.like(Receipt::getProductName, search);
        }
        return Result.success(receiptPage);
    }

}

