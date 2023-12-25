package com.hxy.OnlineDiningRoom.dao;

import com.hxy.OnlineDiningRoom.pojo.News;
import com.hxy.OnlineDiningRoom.pojo.NewsExample;

import java.util.List;

public interface NewsMapper extends CrudDao<News> {

    List<News> selectByExample(NewsExample example);

    /**
     * 资讯审核
     * @param zid
     */
    void audit(int zid);

}