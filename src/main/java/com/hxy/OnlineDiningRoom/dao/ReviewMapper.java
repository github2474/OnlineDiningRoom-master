package com.hxy.OnlineDiningRoom.dao;

import com.hxy.OnlineDiningRoom.pojo.Review;
import com.hxy.OnlineDiningRoom.pojo.ReviewExample;

import java.util.List;

public interface ReviewMapper extends CrudDao<Review>{

    List<Review> selectByExample(ReviewExample example);

}