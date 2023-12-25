package com.hxy.OnlineDiningRoom.dao;

import com.hxy.OnlineDiningRoom.pojo.Product;
import com.hxy.OnlineDiningRoom.pojo.ProductExample;
import com.hxy.OnlineDiningRoom.pojo.ProductVO;

import java.util.List;

public interface ProductMapper extends CrudDao<Product>{

    List<Product> selectByExample(ProductExample example);

    /**
     * 商品上线
     * @param name
     */
    void enableStatus(String name);

    /**
     * 商品下线
     * @param name
     */
    void stopStatus(String name);

    /**
     * 设置图片保存的位置
     * @param vo
     */
    void setImageUrl(ProductVO vo);

    /**
     * 随机五条商品
     * @return
     */
    List<Product> randFive();

    boolean findProByCid(int cid);

}