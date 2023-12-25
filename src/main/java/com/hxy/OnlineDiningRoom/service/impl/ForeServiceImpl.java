package com.hxy.OnlineDiningRoom.service.impl;

import com.hxy.OnlineDiningRoom.dao.CategoryMapper;
import com.hxy.OnlineDiningRoom.dao.ProductMapper;
import com.hxy.OnlineDiningRoom.pojo.Category;
import com.hxy.OnlineDiningRoom.pojo.CategoryExample;
import com.hxy.OnlineDiningRoom.pojo.Product;
import com.hxy.OnlineDiningRoom.service.ForeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForeServiceImpl implements ForeService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Category> listToThree() {
        CategoryExample example = new CategoryExample();
        example.createCriteria().andIdBetween(1,4); //从1开始计数
        List<Category> categories = categoryMapper.selectByExample(example);
        return categories;
    }

    @Override
    public List<Product> getFivePro() {
        return productMapper.randFive();
    }


}
