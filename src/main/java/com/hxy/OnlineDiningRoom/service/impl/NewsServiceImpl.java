package com.hxy.OnlineDiningRoom.service.impl;

import com.hxy.OnlineDiningRoom.dao.CustomerMapper;
import com.hxy.OnlineDiningRoom.dao.NewsMapper;
import com.hxy.OnlineDiningRoom.pojo.Customer;
import com.hxy.OnlineDiningRoom.pojo.News;
import com.hxy.OnlineDiningRoom.pojo.NewsExample;
import com.hxy.OnlineDiningRoom.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<News> list() {
        NewsExample example = new NewsExample();
        example.createCriteria().andStatusEqualTo(1);
        List<News> news = newsMapper.selectByExample(example);
        for (News z:news){
            Customer customer =customerMapper.selectByPrimaryKey(z.getCstid());
            z.setCustomer(customer);
        }
        return news;
    }

    @Override
    public void save(News entity) {
        newsMapper.insert(entity);
    }

    @Override
    public void del(int id) {
        newsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public News get(int id) {
        return newsMapper.selectByPrimaryKey(id);
    }

    @Override
    public void audit(int zid) {
        newsMapper.audit(zid);
    }

    @Override
    public List<News> list1() {
        List<News> news = newsMapper.selectByExample(null);
        for (News z:news){
            Customer customer =customerMapper.selectByPrimaryKey(z.getCstid());
            z.setCustomer(customer);
        }
        return news;
    }
}
