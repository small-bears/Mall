package com.trytry.vo;


import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryVO {
    private Integer categoryId;
    private String categoryName;
    private Integer categoryNum;
    private List<ProductCategoryVO> children;

    public ProductCategoryVO(Integer categoryId, String categoryName,Integer categoryNum) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryNum = categoryNum;
    }
}
