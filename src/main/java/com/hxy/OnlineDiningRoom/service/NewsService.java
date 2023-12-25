package com.hxy.OnlineDiningRoom.service;

import com.hxy.OnlineDiningRoom.pojo.News;

import java.util.List;

/**
 * 资讯
 */
public interface NewsService extends CrudService<News> {

    /**
     * 资讯审核通过
     * @param zid
     */
    void audit(int zid);

    /**
     * 连带未审核的资讯
     * @return
     */
    List<News> list1();

}
