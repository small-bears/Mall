package com.trytry.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trytry.common.lang.Result;
import com.trytry.domain.Good;
import com.trytry.domain.Info;
import com.trytry.domain.Master;
import com.trytry.mapper.GoodMapper;
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
@RequestMapping("/good")
public class GoodController {

    @Resource
    private GoodMapper goodMapper;


    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Integer id){
        goodMapper.deleteById(id);
        return Result.success();
    }
    @GetMapping("/findAll")
    public List<Good> findAll(){
        List<Good> goods = goodMapper.selectList(null);
        return goods;
    }
    @GetMapping("/finds/{customerId}")
    public Result<?> findCustomerId(@RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "1000") Integer pageSize,
                                    @PathVariable Integer customerId){
        Page<Good> page = goodMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Good>lambdaQuery().eq(Good::getCustomerId, customerId));
        return Result.success(page);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody Good good){
        goodMapper.updateById(good);
        return Result.success();
    }
    @PostMapping("/add")
    public Result<?> insert(@RequestBody Good good){
        goodMapper.insert(good);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids){
        goodMapper.deleteBatchIds(ids);
        return Result.success();
    }
    @GetMapping("/find/{id}")
    public Good find(@PathVariable("id") Integer id){
        return this.goodMapper.selectById(id);
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        new Page<>(pageNum, pageSize);
        Page<Good> goodPage = goodMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Good>lambdaQuery().like(Good::getGoodDescribe, search));
        LambdaQueryWrapper<Good> query = Wrappers.<Good>lambdaQuery().orderByDesc(Good::getGoodId);
        if (StrUtil.isNotBlank(search)) {
            query.like(Good::getGoodDescribe, search);
        }
        return Result.success(goodPage);
    }
}

