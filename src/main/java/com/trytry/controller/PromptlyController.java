package com.trytry.controller;

import com.trytry.domain.Promptly;
import com.trytry.mapper.PromptlyMapper;
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
@RequestMapping("/promptly")
public class PromptlyController {

    @Autowired
    private PromptlyMapper promptlyMapper;

    @GetMapping("/findAll")
    public List<Promptly> findAll(){
        List<Promptly> promptlys = promptlyMapper.selectList(null);
        return promptlys;
    }

    @DeleteMapping("/delete/{id}")
    public int delete(@PathVariable("id") Integer id){
        return promptlyMapper.deleteById(id);
    }

    @GetMapping("/find/{id}")
    public Promptly find(@PathVariable("id") Integer id){
        return this.promptlyMapper.selectById(id);
    }

    @PutMapping("/update")
    public int update(@RequestBody Promptly promptly){
        return this.promptlyMapper.updateById(promptly);
    }

    @PostMapping("/add")
    public int insert(@RequestBody Promptly promptly){
        return this.promptlyMapper.insert(promptly);
    }



}

