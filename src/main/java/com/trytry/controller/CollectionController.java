package com.trytry.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trytry.common.lang.Result;
import com.trytry.domain.Collection;
import com.trytry.domain.Master;
import com.trytry.domain.Shopping;
import com.trytry.mapper.CollectionMapper;
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
@RequestMapping("/collection")
public class CollectionController {

    @Resource
    private CollectionMapper collectionMapper;

    @GetMapping("/findAll")
    public List<Collection> findAll(){
        List<Collection> collections = collectionMapper.selectList(null);
        return collections;
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id){
        collectionMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/find/{id}")
    public Collection find(@PathVariable("id") Integer id){
        return this.collectionMapper.selectById(id);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody Collection collection){
        collectionMapper.updateById(collection);
        return Result.success();
    }
    @GetMapping("/finds/{customerId}")
    public Result<?> findCustomerId(@RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                    @PathVariable Integer customerId){
        Page<Collection> page = collectionMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Collection>lambdaQuery().eq(Collection::getCustomerId, customerId));
        return Result.success(page);
    }

    @PostMapping("/add")
    public Result<?> insert(@RequestBody Collection collection){
        collectionMapper.insert(collection);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids){
        collectionMapper.deleteBatchIds(ids);
        return Result.success();
    }


    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        new Page<>(pageNum, pageSize);
        Page<Collection> collectionPage = collectionMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Collection>lambdaQuery().like(Collection::getProductName, search));
        LambdaQueryWrapper<Collection> query = Wrappers.<Collection>lambdaQuery().orderByDesc(Collection::getProductId);
        if (StrUtil.isNotBlank(search)) {
            query.like(Collection::getProductName, search);
        }
        return Result.success(collectionPage);
    }
}

