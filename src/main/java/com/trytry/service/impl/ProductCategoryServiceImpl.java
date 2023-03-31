package com.trytry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.trytry.domain.ProductCategory;
import com.trytry.mapper.InfoMapper;
import com.trytry.mapper.ProductCategoryMapper;
import com.trytry.service.ProductCategorySerivce;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trytry.vo.ProductCategoryVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 分类信息表 服务实现类
 * </p>
 *
 * @author 我自己
 * @since 2021-11-15
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategorySerivce {
    @Resource
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public List<ProductCategoryVO> getAllProductCategoryVO() {
        //一级分类嵌套二级分类
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("category_level",1);
        List<ProductCategory> levelOne = productCategoryMapper.selectList(wrapper);
        //转成VO
        List<ProductCategoryVO> levelOneVO = levelOne.stream().map(e -> new ProductCategoryVO(e.getCategoryId(),e.getCategoryName(),e.getCategoryNum())).collect(Collectors.toList());
        for (ProductCategoryVO productCategoryVO : levelOneVO) {
            wrapper = new QueryWrapper();
            wrapper.eq("category_level",2);
            wrapper.eq("parent_id",productCategoryVO.getCategoryId());
            List<ProductCategory> levelTwo = productCategoryMapper.selectList(wrapper);
            List<ProductCategoryVO> levelTwoVO = levelTwo.stream().map(e -> new ProductCategoryVO(e.getCategoryId(),e.getCategoryName(),e.getCategoryNum())).collect(Collectors.toList());
            productCategoryVO.setChildren(levelTwoVO);
        }


        return levelOneVO;
    }
}
