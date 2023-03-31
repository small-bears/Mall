package com.trytry.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.trytry.common.lang.Result;
import com.trytry.domain.Assess;
import com.trytry.domain.Shopping;
import com.trytry.mapper.AssessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/assess")
public class AssessController {

    @Autowired
    private  AssessMapper  assessMapper;

    @GetMapping("/findAll")
    public List<Assess> findAll(){
        List<Assess>  assesss = assessMapper.selectList(null);
        return  assesss;
    }
    @GetMapping("/finds/{customerId}")
    public Result<?> findCustomerId(@RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                    @PathVariable Integer customerId){
        Page<Assess> page = assessMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Assess>lambdaQuery().eq(Assess::getCustomerId, customerId));
        return Result.success(page);
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id){
        assessMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/find/{id}")
    public Assess find(@PathVariable("id") Integer id){
        return this.assessMapper.selectById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Assess assess){
        assessMapper.updateById(assess);
        return Result.success();
    }

    @PostMapping("/add")
    public Result insert(@RequestBody Assess assess){
        assessMapper.insert(assess);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids){
        assessMapper.deleteBatchIds(ids);
        return Result.success();
    }
    @GetMapping("/count")
    public Result Count(){
        Integer count = assessMapper.selectCount(null);
        return Result.success(count);
    }


    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        new Page<>(pageNum, pageSize);
        Page<Assess> assessPage = assessMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Assess>lambdaQuery().like(Assess::getProductName, search));
        LambdaQueryWrapper<Assess> query = Wrappers.<Assess>lambdaQuery().orderByDesc(Assess::getProductId);
        if (StrUtil.isNotBlank(search)) {
            query.like(Assess::getProductName, search);
        }
        return Result.success(assessPage);
    }

}

