package com.hxy.OnlineDiningRoom.controller;

import com.hxy.OnlineDiningRoom.pojo.News;
import com.hxy.OnlineDiningRoom.service.NewsService;
import com.hxy.OnlineDiningRoom.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class NewsControllerTest {

    @Mock
    private NewsService newsService;

    @InjectMocks
    private NewsController newsController;

    private Model model;
    private Page page;

    @BeforeEach
    public void setUp() {
        model = new ExtendedModelMap();
        page = new Page();
    }

    @Test
    public void testList() {
        // Arrange
        List<News> mockedList = new ArrayList<>();
        mockedList.add(new News());
        when(newsService.list1()).thenReturn(mockedList);

        int start = 0;
        int count = 10;
        page.setStart(start);
        page.setCount(count);

        // Act
        String viewName = newsController.list(page, model);

        // Assert
        verify(newsService).list1();
        assertEquals("cstpage/news-list", viewName);
        // 从模型中获取'list'属性并与mockedList进行比较
        List<News> actualList = (List<News>) model.asMap().get("list");
        assertEquals(mockedList, actualList);

        // 从模型中获取'totals'属性并与mockedList的大小进行比较
        Integer actualTotal = (Integer) model.asMap().get("totals");
        assertEquals(mockedList.size(), actualTotal);
    }

    @Test
    public void testNewsAudit() {
        // Arrange
        int zid = 1;
        doNothing().when(newsService).audit(zid);

        // Act
        String result = newsController.newsAudit(zid);

        // Assert
        verify(newsService).audit(zid);
        assertEquals("success", result);
    }

    @Test
    public void testDel() {
        // Arrange
        int id = 1;
        doNothing().when(newsService).del(id);

        // Act
        String viewName = newsController.del(id);

        // Assert
        verify(newsService).del(id);
        assertEquals("redirect:list", viewName);
    }
}
