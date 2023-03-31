package com.trytry.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trytry.common.lang.Result;
import com.trytry.domain.Feedback;
import com.trytry.mapper.FeedbackMapper;
import com.trytry.service.FeedbackService;
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
@RequestMapping("/feedback")
public class FeedbackController {

    @Resource
    private FeedbackMapper feedbackMapper;


    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Integer id){
        feedbackMapper.deleteById(id);
        return Result.success();
    }
    @GetMapping("/find/{id}")
    public Feedback find(@PathVariable("id") Integer id){
        return this.feedbackMapper.selectById(id);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody Feedback feedback){
        feedbackMapper.updateById(feedback);
        return Result.success();
    }

    @PostMapping("/add")
    public Result<?> insert(@RequestBody Feedback feedback){
        feedbackMapper.insert(feedback);
        return Result.success();
    }
    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids){
        feedbackMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        new Page<>(pageNum, pageSize);
        Page<Feedback> feedbackPage = feedbackMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Feedback>lambdaQuery().like(Feedback::getFeedbackBt, search));
        LambdaQueryWrapper<Feedback> query = Wrappers.<Feedback>lambdaQuery().orderByDesc(Feedback::getFeedbackId);
        if (StrUtil.isNotBlank(search)) {
            query.like(Feedback::getFeedbackBt, search);
        }
        return Result.success(feedbackPage);
    }


}

