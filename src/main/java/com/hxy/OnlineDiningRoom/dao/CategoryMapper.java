package com.hxy.OnlineDiningRoom.dao;

import com.hxy.OnlineDiningRoom.pojo.Category;
import com.hxy.OnlineDiningRoom.pojo.CategoryExample;

import java.util.List;

public interface CategoryMapper extends CrudDao<Category>{

     List<Category> selectByExample(CategoryExample example);

}