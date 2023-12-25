package com.hxy.OnlineDiningRoom.controller;

import com.hxy.OnlineDiningRoom.pojo.News;
import com.hxy.OnlineDiningRoom.service.NewsService;
import com.hxy.OnlineDiningRoom.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("/list")
    public String list(Page page, Model model){
        PageHelper.offsetPage(page.getStart(),page.getCount());//分页查询
        List<News> list = newsService.list1();
        int total = (int) new PageInfo<>(list).getTotal();//总条数
        page.setTotal(total);

        model.addAttribute("list",list);
        model.addAttribute("totals",total);

        return "cstpage/news-list";
    }

    /**
     * 审核
     * @param zid
     * @return
     */
    @RequestMapping("/success")
    @ResponseBody
    public String newsAudit(int zid){
        newsService.audit(zid);
        return "success";
    }

    @RequestMapping("/del")
    public String del(int id){
        newsService.del(id);
        return "redirect:list";
    }

}
