package com.trytry.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trytry.common.lang.Result;
import com.trytry.domain.Collection;
import com.trytry.domain.Good;
import com.trytry.domain.Img;
import com.trytry.mapper.ImgMapper;
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
@RequestMapping("/img")
public class ImgController {

    @Resource
    private ImgMapper imgMapper;

    @GetMapping("/findAll")
    public List<Img> findAll(){
        List<Img> img = imgMapper.selectList(null);
        return img;
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id){
        imgMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/find/{id}")
    public Img find(@PathVariable("id") Integer id){
        return this.imgMapper.selectById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Img img){
        imgMapper.updateById(img);
        return Result.success();
    }

    @PostMapping("/add")
    public Result insert(@RequestBody Img img){
        imgMapper.insert(img);
        return Result.success();
    }
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        imgMapper.deleteBatchIds(ids);
        return Result.success();
    }
    @GetMapping("/finds/{customerId}")
    public Result<?> findCustomerId(@RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                    @PathVariable Integer customerId){
        Page<Img> page = imgMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Img>lambdaQuery().eq(Img::getCustomerId, customerId).orderByDesc(Img::getCreateTime));
        return Result.success(page);
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        new Page<>(pageNum, pageSize);
        Page<Img> imgPage = imgMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Img>lambdaQuery().like(Img::getProductName, search).orderByDesc(Img::getCreateTime));
        LambdaQueryWrapper<Img> query = Wrappers.<Img>lambdaQuery().orderByDesc(Img::getCreateTime);
        if (StrUtil.isNotBlank(search)) {
            query.like(Img::getProductName, search);
        }
        return Result.success(imgPage);
    }

}

