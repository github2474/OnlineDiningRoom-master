package com.hxy.OnlineDiningRoom.service;

import com.hxy.OnlineDiningRoom.pojo.Category;

public interface CategoryService extends CrudService<Category> {
    /**
     * 更新分类
     * @param category
     */
    public void update(Category category);
}
