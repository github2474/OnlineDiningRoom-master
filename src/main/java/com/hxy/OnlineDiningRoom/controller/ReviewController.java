package com.hxy.OnlineDiningRoom.controller;

import com.hxy.OnlineDiningRoom.pojo.Review;
import com.hxy.OnlineDiningRoom.service.ProductService;
import com.hxy.OnlineDiningRoom.service.ReviewService;
import com.hxy.OnlineDiningRoom.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 评论模块controller
 */
@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ProductService productService;

    @RequestMapping("/list")
    public String list(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());//分页查询
        List<Review> list= reviewService.list();
        int total = (int) new PageInfo<>(list).getTotal();//总条数
        page.setTotal(total);

        model.addAttribute("totals",list.size());
        model.addAttribute("list",list);
        return "pinglunpage/pinglun";
    }

    @RequestMapping("/del")
    public String del(int id){
        reviewService.del(id);
        return "redirect:list";
    }

}
