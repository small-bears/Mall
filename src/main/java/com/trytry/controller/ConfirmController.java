package com.trytry.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trytry.common.lang.Result;
import com.trytry.domain.Confirm;
import com.trytry.domain.Shopping;
import com.trytry.mapper.ConfirmMapper;
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
@RequestMapping("/confirm")
public class ConfirmController {

    @Resource
    private ConfirmMapper confirmMapper;

    @GetMapping("/findAll")
    public List<Confirm> findAll(){
        List<Confirm> confirms = confirmMapper.selectList(null);
        return confirms;
    }

    @GetMapping("/finds/{customerId}")
    public Result<?> findCustomerId(@RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                    @PathVariable Integer customerId){
        Page<Confirm> page = confirmMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Confirm>lambdaQuery().eq(Confirm::getCustomerId, customerId));
        return Result.success(page);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id){
        confirmMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/find/{id}")
    public Confirm find(@PathVariable("id") Integer id){
        return this.confirmMapper.selectById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Confirm confirm){
        confirmMapper.updateById(confirm);
        return Result.success();
    }

    @PostMapping("/add")
    public Result insert(@RequestBody Confirm confirm){
        confirmMapper.insert(confirm);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        confirmMapper.deleteBatchIds(ids);
        return Result.success();
    }
    @GetMapping("/count")
    public Result Count(){
        Integer count = confirmMapper.selectCount(null);
        return Result.success(count);
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        new Page<>(pageNum, pageSize);
        Page<Confirm> confirmPage = confirmMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Confirm>lambdaQuery().like(Confirm::getProductName, search));
        LambdaQueryWrapper<Confirm> query = Wrappers.<Confirm>lambdaQuery().orderByDesc(Confirm::getProductId);
        if (StrUtil.isNotBlank(search)) {
            query.like(Confirm::getProductName, search);
        }
        return Result.success(confirmPage);
    }

}

